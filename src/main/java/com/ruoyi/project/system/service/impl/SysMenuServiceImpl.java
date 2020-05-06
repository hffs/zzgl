package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.TenantUtil;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.domain.vo.MetaVo;
import com.ruoyi.project.system.domain.vo.RouterVo;
import com.ruoyi.project.system.mapper.SysMenuMapper;
import com.ruoyi.project.system.mapper.SysRoleMenuMapper;
import com.ruoyi.project.system.mapper.SysTenantMenuMapper;
import com.ruoyi.project.system.mapper.SysUserRoleMapper;
import com.ruoyi.project.system.service.ISysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Resource
    private SysMenuMapper menuMapper;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Resource
    private SysTenantMenuMapper tenantMenuMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    /**
     * 查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu)
    {
        return menuMapper.selectMenuList(menu);
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenu selectMenuById(Long menuId)
    {
        return menuMapper.selectMenuById(menuId);
    }

    @Override
    public List<TreeSelect> treeselect(boolean isAll)
    {
        List<SysMenu> menus = null;
        // 超级管理员
        if (SecurityUtils.isSuper()){
            menus = formatMenu(selectMenuList(new SysMenu()), !isAll);
        }
        // 租户管理员
        else {
            Long roleId = TenantUtil.getTenant().getRoleId();
            menus = menuMapper.selectMenuListByTenantRoleId(roleId);
            menus = formatMenu(menus, false);
        }
        return buildMenuTreeSelect(menus);
    }

    private List<SysMenu> formatMenu(List<SysMenu> menus, boolean isSuper){
        if (CollectionUtils.isEmpty(menus)){
            return null;
        }
        if (isSuper){
            menus.forEach(menu -> {
                menu.setMenuName(menu.getMenuName().replace("租户-", ""));
            });
            menus = menus.stream().filter(menu -> !menu.getMenuName().startsWith("平台-")).collect(Collectors.toList());
        } else {
            menus.forEach(menu -> {
                menu.setMenuName(menu.getMenuName().replace("租户-", ""));
            });
        }
        return menus;
    }

    private List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus)
    {
        List<SysMenu> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId)
    {
        Long tenantRoleId = TenantUtil.getTenant().getRoleId();
        List<SysMenu> list = menuMapper.selectMenuListByRoleIds(tenantRoleId, Arrays.asList(roleId), true);
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().map(SysMenu::getMenuId).collect(Collectors.toList());
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(SysMenu menu)
    {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenu menu)
    {
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenu menu)
    {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId)
    {
        return menuMapper.hasChildByMenuId(menuId) > 0;
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int deleteMenuById(Long menuId)
    {
        // 删除菜单
        menuMapper.deleteMenuById(menuId);
        // 删除租户菜单
        tenantMenuMapper.deleteByMenuId(menuId);
        // 删除角色菜单
        roleMenuMapper.deleteByMenuId(menuId);
        return 1;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuByUserId(Long userId)
    {
        Set<String> permsSet = new HashSet<>();
        List<SysMenu> list = queryByUserId(userId);
        if (!CollectionUtils.isEmpty(list)){
            List<String> perms = list.stream().map(SysMenu::getPerms).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(perms)){
                for (String perm : perms)
                {
                    if (StringUtils.isNotEmpty(perm))
                    {
                        permsSet.addAll(Arrays.asList(perm.trim().split(",")));
                    }
                }
            }
        }
        return permsSet;
    }

    private List<SysMenu> queryByUserId(Long userId){
        List<Long> roleIds = userRoleMapper.queryRoleByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)){
            return null;
        }
        Long tenantRoleId = TenantUtil.getTenant().getRoleId();
        return menuMapper.selectMenuListByRoleIds(tenantRoleId, roleIds, false);
    }

    /**
     * 根据租户ID查询权限
     *
     * @param roleId 租户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuByTenantRoleId(Long roleId)
    {
        Set<String> permsSet = new HashSet<>();
        List<SysMenu> list = menuMapper.selectMenuListByTenantRoleId(roleId);
        if (!CollectionUtils.isEmpty(list)){
            List<String> perms =  list.stream().map(SysMenu::getPerms).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(perms)){
                for (String perm : perms)
                {
                    if (StringUtils.isNotEmpty(perm))
                    {
                        permsSet.addAll(Arrays.asList(perm.trim().split(",")));
                    }
                }
            }
        }
        return permsSet;
    }

    /**
     * 根据用户名称查询菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<RouterVo> selectMenuTree(SysUser user)
    {
        List<SysMenu> menus = null;
        // 超级管理员
        if (SecurityUtils.isSuper()){
            menus = formatRouter(selectMenuList(new SysMenu()), true);
        }
        else {
            // 租户管理员
            if (SecurityUtils.isAdmin(user)){
                Long roleId = TenantUtil.getTenant().getRoleId();
                menus = menuMapper.selectMenuListByTenantRoleId(roleId);
            }
            // 租户用户
            else {
                menus = queryByUserId(user.getUserId());
            }
            menus = formatRouter(menus, false);
        }

        return buildMenus(getChildPerms(menus, 0L));
    }

    private List<SysMenu> formatRouter(List<SysMenu> menus, boolean isSuper){
        if (CollectionUtils.isEmpty(menus)){
            return null;
        }
        if (isSuper){
            menus.forEach(menu -> {
                menu.setMenuName(menu.getMenuName().replace("平台-", ""));
            });
            menus = menus.stream().filter(menu -> !menu.getMenuName().startsWith("租户-")).collect(Collectors.toList());
        } else {
            menus.forEach(menu -> {
                menu.setMenuName(menu.getMenuName().replace("租户-", ""));
            });
        }
        return menus;
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    private List<RouterVo> buildMenus(List<SysMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setName(StringUtils.capitalize(menu.getPath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<SysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && "M".equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext();)
        {
            SysMenu t = iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == 0)
            {
                recursionFn(menus, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 获取路由地址
     * 
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(SysMenu menu)
    {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录
        if (0 == menu.getParentId() && "1".equals(menu.getIsFrame()))
        {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    private List<SysMenu> getChildPerms(List<SysMenu> list, Long parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SysMenu t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId().equals(parentId))
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext())
                {
                    SysMenu n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
            SysMenu n = it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0;
    }
}
