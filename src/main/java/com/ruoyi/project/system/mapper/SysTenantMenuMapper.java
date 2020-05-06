package com.ruoyi.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.system.domain.SysTenantMenu;

import java.util.List;

/**
 * 租户和菜单关联Mapper接口
 * 
 * @author wfq
 * @date 2020-02-11
 */
public interface SysTenantMenuMapper extends BaseMapper<SysTenantMenu>
{

    List<Long> queryByRoleId(Long roleId);

    Integer deleteByRoleId(Long roleId);

    Integer batchSaveByRoleId(List<SysTenantMenu> list);

    Integer deleteByMenuId(Long menuId);
}
