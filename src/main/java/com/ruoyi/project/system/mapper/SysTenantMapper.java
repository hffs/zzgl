package com.ruoyi.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.system.domain.SysTenant;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统租户mapper接口
 *
 * @author wfq
 * @date 2020-02-08
 */
public interface SysTenantMapper extends BaseMapper<SysTenant>
{

    List<SysTenant> queryList(SysTenant sysTenant);

    SysTenant queryByTenantNo(@Param("tenantNo") String tenantNo);

    Integer hasTenantNo(SysTenant sysTenant);

    Integer updateTenant(SysTenant sysTenant);

    Integer updateStatus(SysTenant sysTenant);

    Integer removeTenant(Long tenantId);

    /**
     * 查询租户正常数
     * @return 正常租户数量
     */
    @Select("SELECT COUNT(*) FROM sys_tenant a WHERE a.tenant_type = '1' AND a.STATUS = '0'")
    Integer sysTenantCharTnormal();

    /**
     * 查询 租户停用数量
     * @return 停用租户数量
     */
    @Select("SELECT COUNT(*) FROM sys_tenant a WHERE a.tenant_type = '1' AND a.STATUS = '1'")
    Integer sysTenantDiscontinue();

    Integer queryByRoleId(Long roleId);
}
