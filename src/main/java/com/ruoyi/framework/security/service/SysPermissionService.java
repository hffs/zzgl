package com.ruoyi.framework.security.service;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.TenantUtil;
import com.ruoyi.project.system.domain.SysTenant;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 * 
 * @author ruoyi
 */
@Component
public class SysPermissionService
{

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     * 
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user)
    {
        Set<String> roles = new HashSet<String>();
        if (SecurityUtils.isSuper()){
            // 平台管理员
            roles.add("super");
        } else if (SecurityUtils.isAdmin(user)){
            // 租户管理员
            roles.add("admin");
        } else {
            roles.add("user");
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user)
    {
        SysTenant tenant = TenantUtil.getTenant();
        Set<String> menus = new HashSet<String>();
        if (SecurityUtils.isSuper()){
            // 平台管理员
            menus.add("*:*:*");
        } else if (SecurityUtils.isAdmin(user)){
            // 租户管理员
            menus.addAll(menuService.selectMenuByTenantRoleId(tenant.getRoleId()));
        } else {
            // 租户用户
            menus.addAll(menuService.selectMenuByUserId(user.getUserId()));
        }
        return menus;
    }



}
