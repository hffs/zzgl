package com.ruoyi.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.system.domain.SysTenantRole;

import java.util.List;

/**
 * 系统租户角色Service接口
 * 
 * @author wfq
 * @date 2020-03-06
 */
public interface ISysTenantRoleService extends IService<SysTenantRole>
{

    /**
     * 判断name是否存在
     * @param sysTenantRole
     * @return
     */
    Integer hasName(SysTenantRole sysTenantRole);

    /**
     * 保存角色
     * @param sysTenantRole
     * @return
     */
    boolean saveRole(SysTenantRole sysTenantRole);

    /**
     * 更新角色
     * @param sysTenantRole
     * @return
     */
    boolean updateRole(SysTenantRole sysTenantRole);

    /**
     * 获取角色详情
     * @param id
     * @return
     */
    SysTenantRole getInfo(Long id);

    /**
     * 查询角色列表
     *
     * @return
     */
    List<SysTenantRole> queryList(SysTenantRole sysTenantRole);
}
