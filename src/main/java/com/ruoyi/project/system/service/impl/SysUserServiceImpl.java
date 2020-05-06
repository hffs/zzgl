package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.constant.ConfigConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysPost;
import com.ruoyi.project.system.domain.SysRole;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.domain.SysUserRole;
import com.ruoyi.project.system.mapper.SysRoleMapper;
import com.ruoyi.project.system.mapper.SysUserMapper;
import com.ruoyi.project.system.mapper.SysUserRoleMapper;
import com.ruoyi.project.system.service.ISysConfigService;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysPostService;
import com.ruoyi.project.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private ISysPostService postService;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Resource
    private ISysDeptService deptService;

    @Resource
    private ISysConfigService configService;

    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<SysUser> selectUserList(SysUser user)
    {
        if (user.getDeptId() != null && user.getDeptId() != 0L){
            List<Long> deptIds = deptService.queryChildId(user.getDeptId());
            String deptIdStr = "";
            if (!CollectionUtils.isEmpty(deptIds)){
                for (Long deptId : deptIds){
                    deptIdStr = deptId + ",";
                }
            }
            user.setDeptIdStr(deptIdStr + user.getDeptId());
        }
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectByUserName(String userName)
    {
        return userMapper.selectByUserName(userName);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    @Override
    public List<SysUser> selectALLUser() {
        return userMapper.selectALLUser();
    }

    /**
     * 查询用户所属角色组
     * 
     * @param userId 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId)
    {
        List<SysRole> list = roleMapper.selectRoleByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     * 
     * @param userId
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId)
    {
        SysPost post = postService.getById(userId);
        if (post != null){
            return post.getPostName();
        }
        return "";
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName)
    {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public boolean insertUser(SysUser user)
    {
        // 新增用户信息
        boolean result = userMapper.insertUser(user) > 0;
        // 新增用户与角色管理
        insertUserRole(user);
        return result;
    }

    /**
     * 修改保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUser user)
    {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.deleteByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户状态
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户基本信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 重置用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    @Override
    public int resetTenantPwd(Long tenantId, String password) {
        return userMapper.resetTenantPwd(tenantId, password);
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user)
    {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles))
        {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles)
            {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
                //如果当前用户是超级管理员，则绑定用户租户id为自己id，否则绑定租户id为自己公司管理员的id
            }
            if (list.size() > 0)
            {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey(ConfigConstants.USER_PASSWORD);
        for (SysUser user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                SysUser u = userMapper.selectByUserName(user.getUserName());
                if (StringUtils.isNull(u))
                {
                    user.setPassword(SecurityUtils.encryptPassword(password));
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public boolean saveTenantUser(SysUser sysUser) {
        return userMapper.saveTenantUser(sysUser);
    }

    @Override
    public boolean hasDeptUser(Long deptId) {
        return userMapper.checkDept(deptId) > 0;
    }

    @Override
    public boolean hasPostUser(Long postId) {
        return userMapper.checkPost(postId) > 0;
    }

}
