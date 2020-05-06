package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysTenantMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 租户和菜单关联Service接口
 * 
 * @author wfq
 * @date 2020-02-11
 */
public interface ISysTenantMenuService extends IService<SysTenantMenu>
{

    List<Long> queryByRoleId(Long roleId);

    boolean batchSaveByRoleId(Long roleId, Long[] menuIds);

}
