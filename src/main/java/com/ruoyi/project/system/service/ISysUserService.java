package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysUser;

import java.util.List;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface ISysUserService
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectByUserName(String userName);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUser selectUserById(Long userId);

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> selectALLUser();

    /**
     * 根据用户ID查询用户所属角色组
     * 
     * @param userId
     * @return 结果
     */
    String selectUserRoleGroup(Long userId);

    /**
     * 根据用户ID查询用户所属岗位组
     * 
     * @param userId
     * @return 结果
     */
    String selectUserPostGroup(Long userId);

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名称
     * @return 结果
     */
    String checkUserNameUnique(String userName);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    boolean insertUser(SysUser user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);

    /**
     * 修改用户状态
     * 
     * @param user 用户信息
     * @return 结果
     */
    int updateUserStatus(SysUser user);

    /**
     * 修改用户基本信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    int updateUserProfile(SysUser user);

    /**
     * 重置用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    int resetPwd(SysUser user);

    /**
     * 重置租户密码
     * @param tenantId
     * @param password
     * @return
     */
    int resetTenantPwd(Long tenantId, String password);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserById(Long userId);

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);

    /**
     * 保存租户用户
     * @param sysUser
     * @return
     */
    boolean saveTenantUser(SysUser sysUser);

    /**
     * 是否存在部门用户
     * @param deptId
     * @return
     */
    boolean hasDeptUser(Long deptId);

    /**
     * 是否存在岗位用户
     * @param postId
     * @return
     */
    boolean hasPostUser(Long postId);
}
