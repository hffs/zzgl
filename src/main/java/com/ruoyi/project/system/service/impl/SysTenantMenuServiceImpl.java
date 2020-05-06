package com.ruoyi.project.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.system.domain.SysTenantMenu;
import com.ruoyi.project.system.mapper.SysTenantMenuMapper;
import com.ruoyi.project.system.service.ISysTenantMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 租户和菜单关联Service业务层处理
 * 
 * @author wfq
 * @date 2020-02-11
 */
@Service
public class SysTenantMenuServiceImpl extends ServiceImpl<SysTenantMenuMapper, SysTenantMenu> implements ISysTenantMenuService
{

    @Resource
    private SysTenantMenuMapper tenantMenuMapper;

    @Override
    public List<Long> queryByRoleId(Long roleId) {
        return tenantMenuMapper.queryByRoleId(roleId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean batchSaveByRoleId(Long roleId, Long[] menuIds) {
        // 1、删除租户对应的权限
        tenantMenuMapper.deleteByRoleId(roleId);
        // 2、批量保存对应的权限
        List<SysTenantMenu> list = new ArrayList<>();
        if(menuIds != null){
            for(Long menuId : menuIds){
                list.add(new SysTenantMenu(roleId, menuId));
            }
        }
        boolean result = true;
        if (!CollectionUtils.isEmpty(list)){
            result = tenantMenuMapper.batchSaveByRoleId(list) > 0;
        }
        return result;
    }
}
