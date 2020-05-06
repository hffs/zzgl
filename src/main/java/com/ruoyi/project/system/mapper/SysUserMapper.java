package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
public interface SysUserMapper
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser sysUser);

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
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUser user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名称
     * @return 结果
     */
    int checkUserNameUnique(String userName);

    /**
     * 保存租户用户
     * @param sysUser
     * @return
     */
    boolean saveTenantUser(SysUser sysUser);

    /**
     * 重置租户密码
     * @param tenantId
     * @param password
     * @return
     */
    int resetTenantPwd(@Param("tenantId") Long tenantId, @Param("password") String password);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUserById(Long userId);

    /**
     * 校验部门用户数
     *
     * @param deptId 部门id
     * @return 结果
     */
    int checkDept(Long deptId);

    /**
     * 校验岗位用户数
     *
     * @param postId 岗位id
     * @return 结果
     */
    int checkPost(Long postId);

}
