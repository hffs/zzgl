package com.ruoyi.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.system.domain.SysRole;

import java.util.List;

/**
 * 角色业务层
 * 
 * @author ruoyi
 */
public interface ISysRoleService extends IService<SysRole>
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    List<SysRole> selectRoleAll();

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    String checkRoleNameUnique(SysRole role);

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(SysRole role);

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);

    /**
     * 修改角色状态
     * 
     * @param role 角色信息
     * @return 结果
     */
    int updateRoleStatus(SysRole role);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    boolean deleteRoleById(Long roleId);
}
