package com.ruoyi.common.utils;

import com.ruoyi.project.system.domain.SysTenant;

public class TenantUtil {

    /**
     * 租户信息
     */
    private static SysTenant tenant;

    public static SysTenant getTenant() {
        return tenant;
    }

    public static void setTenant(SysTenant tenant) {
        TenantUtil.tenant = tenant;
    }

    public static void initTenantNo(String tenantNo) {
        TenantUtil.tenant = new SysTenant(tenantNo);
    }
}
