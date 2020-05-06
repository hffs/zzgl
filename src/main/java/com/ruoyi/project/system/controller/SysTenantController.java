package com.ruoyi.project.system.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysTenant;
import com.ruoyi.project.system.domain.SysTenantRole;
import com.ruoyi.project.system.service.ISysTenantRoleService;
import com.ruoyi.project.system.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统租户Controller
 * 
 * @author wfq
 * @date 2020-02-08
 */
@RestController
@RequestMapping("/system/tenant")
public class SysTenantController extends BaseController
{
    @Autowired
    private ISysTenantService sysTenantService;
    @Autowired
    private ISysTenantRoleService sysTenantRoleService;

    /**
     * 查询系统租户列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysTenant sysTenant)
    {
        startPage();
        List<SysTenant> list = sysTenantService.queryList(sysTenant);
        return getDataTable(list);
    }

    /**
     * 新增系统租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:add')")
    @Log(title = "系统租户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysTenant sysTenant)
    {
        return toAjax(sysTenantService.save(sysTenant));
    }

    /**
     * 修改系统租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "系统租户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysTenant sysTenant)
    {
        return toAjax(sysTenantService.updateTenant(sysTenant));
    }

    /**
     * 删除系统租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:remove')")
    @Log(title = "系统租户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tenantId}")
    public AjaxResult remove(@PathVariable Long tenantId)
    {
        return toAjax(sysTenantService.removeTenant(tenantId));
    }

    /**
     * 获取系统租户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:query')")
    @GetMapping(value = "/getInfo/{tenantId}")
    public AjaxResult getInfo(@PathVariable("tenantId") Long tenantId)
    {
        SysTenant sysTenant = sysTenantService.getById(tenantId);
        if (sysTenant != null && sysTenant.getRoleId() != null){
            SysTenantRole sysTenantRole = sysTenantRoleService.getById(sysTenant.getRoleId());
            if (sysTenantRole != null){
                sysTenant.setRoleName(sysTenantRole.getName());
            }
        }
        return AjaxResult.success(sysTenant);
    }

    /**
     * 重置租户密码
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "系统租户", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysTenant sysTenant)
    {
        return toAjax(sysTenantService.resetPwd(sysTenant));
    }

    /**
     * 禁用启用
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "系统租户", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody SysTenant sysTenant)
    {
        return toAjax(sysTenantService.updateStatus(sysTenant));
    }

}
