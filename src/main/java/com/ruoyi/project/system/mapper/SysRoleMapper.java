package com.ruoyi.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.system.domain.SysRole;

import java.util.List;

/**
 * 角色表 数据层
 * 
 * @author ruoyi
 */
public interface SysRoleMapper extends BaseMapper<SysRole>
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 校验角色名称是否唯一
     * 
     * @param roleName 角色名称
     * @return 角色信息
     */
    SysRole checkRoleNameUnique(String roleName);

    /**
     * 修改角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);

}
