package com.ruoyi.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.system.domain.SysTenantRole;

import java.util.List;

/**
 * 系统租户角色Mapper接口
 * 
 * @author wfq
 * @date 2020-03-06
 */
public interface SysTenantRoleMapper extends BaseMapper<SysTenantRole>
{

    /**
     * 判断name是否存在
     * @param sysTenantRole
     * @return
     */
    Integer hasName(SysTenantRole sysTenantRole);

    /**
     * 查询角色列表
     *
     * @return
     */
    List<SysTenantRole> queryList(SysTenantRole sysTenantRole);
}
