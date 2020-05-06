package com.ruoyi.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.system.domain.SysTenant;

import java.util.List;

/**
 * 系统租户Service接口
 *
 * @author wfq
 * @date 2020-02-08
 */
public interface ISysTenantService extends IService<SysTenant>
{

    List<SysTenant> queryList(SysTenant sysTenant);

    SysTenant queryByTenantNo(String tenantNo);

    boolean updateTenant(SysTenant sysTenant);

    boolean removeTenant(Long tenantId);

    boolean updateStatus(SysTenant sysTenant);

    boolean resetPwd(SysTenant sysTenant);

    /**
     * 查询租户正常数
     * @return 正常租户数量
     */
    Integer sysTenantCharTnormal();

    /**
     * 查询 租户停用数量
     * @return 停用租户数量
     */
    Integer sysTenantDiscontinue();

    /**
     * 通过租户id查询
     * @param roleId
     * @return
     */
    Integer queryByRoleId(Long roleId);
}
