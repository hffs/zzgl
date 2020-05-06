package com.ruoyi.project.system.service;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface ISysMenuService
{
    /**
     * 查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 获取菜单下拉树列表
     * @return
     */
    List<TreeSelect> treeselect(boolean isAll);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Long> selectMenuListByRoleId(Long roleId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    String checkMenuNameUnique(SysMenu menu);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenu menu);

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuByUserId(Long userId);

    /**
     * 根据租户角色ID查询权限
     *
     * @param roleId 用户角色ID
     * @return 权限列表
     */
    Set<String> selectMenuByTenantRoleId(Long roleId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param user 用户
     * @return 菜单列表
     */
    List<RouterVo> selectMenuTree(SysUser user);

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    List<SysMenu> buildMenuTree(List<SysMenu> menus);

}
