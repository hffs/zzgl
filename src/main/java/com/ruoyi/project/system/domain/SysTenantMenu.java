package com.ruoyi.project.system.domain;

/**
 * 租户和菜单关联对象 sys_tenant_menu
 * 
 * @author wfq
 * @date 2020-02-11
 */
public class SysTenantMenu
{

    private Long menuId;
    private Long roleId;

    public SysTenantMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
