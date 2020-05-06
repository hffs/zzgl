package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表 数据层
 * 
 * @author ruoyi
 */
public interface SysMenuMapper
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
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenu menu);

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int hasChildByMenuId(Long menuId);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);

    /**
     * 查询系统菜单列表
     *
     * @param roleId 租户角色id
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListByTenantRoleId(Long roleId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleIds 角色ID
     * @return 选中菜单列表
     */
    List<SysMenu> selectMenuListByRoleIds(@Param("tenantRoleId") Long tenantRoleId, @Param("roleIds") List<Long> roleIds, @Param("isFilter") boolean isFilter);

}
