package com.ruoyi.project.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.system.domain.SysTenantRole;
import com.ruoyi.project.system.mapper.SysTenantRoleMapper;
import com.ruoyi.project.system.service.ISysTenantMenuService;
import com.ruoyi.project.system.service.ISysTenantRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统租户角色Service业务层处理
 * 
 * @author wfq
 * @date 2020-03-06
 */
@Service
public class SysTenantRoleServiceImpl extends ServiceImpl<SysTenantRoleMapper, SysTenantRole> implements ISysTenantRoleService
{

    @Resource
    private SysTenantRoleMapper sysTenantRoleMapper;
    @Resource
    private ISysTenantMenuService sysTenantMenuService;

    @Override
    public Integer hasName(SysTenantRole sysTenantRole) {
        return sysTenantRoleMapper.hasName(sysTenantRole);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean saveRole(SysTenantRole sysTenantRole) {
        super.save(sysTenantRole);
        return sysTenantMenuService.batchSaveByRoleId(sysTenantRole.getId(), sysTenantRole.getMenuIds());
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateRole(SysTenantRole sysTenantRole) {
        sysTenantMenuService.batchSaveByRoleId(sysTenantRole.getId(), sysTenantRole.getMenuIds());
        return super.updateById(sysTenantRole);
    }

    @Override
    public SysTenantRole getInfo(Long id) {
        SysTenantRole sysTenantRole = super.getById(id);
        if (sysTenantRole != null){
            List<Long> list = sysTenantMenuService.queryByRoleId(id);
            if (!CollectionUtils.isEmpty(list)){
                sysTenantRole.setMenuIds(list.toArray(new Long[]{}));
            }
        }
        return sysTenantRole;
    }

    @Override
    public List<SysTenantRole> queryList(SysTenantRole sysTenantRole) {
        return sysTenantRoleMapper.queryList(sysTenantRole);
    }
}
