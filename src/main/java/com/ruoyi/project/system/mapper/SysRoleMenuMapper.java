package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysRoleMenu;

import java.util.List;

/**
 * 角色与菜单关联表 数据层
 * 
 * @author ruoyi
 */
public interface SysRoleMenuMapper
{
    /**
     * 通过角色ID删除
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteByRoleId(Long roleId);

    /**
     * 通过菜单ID删除
     *
     * @param menuId 角色ID
     * @return 结果
     */
    int deleteByMenuId(Long menuId);

    /**
     * 批量新增角色菜单信息
     * 
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
