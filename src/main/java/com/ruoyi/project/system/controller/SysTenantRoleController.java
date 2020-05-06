package com.ruoyi.project.system.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysTenantRole;
import com.ruoyi.project.system.service.ISysTenantRoleService;
import com.ruoyi.project.system.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统租户角色Controller
 * 
 * @author wfq
 * @date 2020-03-06
 */
@RestController
@RequestMapping("/tenant/role")
public class SysTenantRoleController extends BaseController
{
    @Autowired
    private ISysTenantRoleService sysTenantRoleService;
    @Autowired
    private ISysTenantService sysTenantService;

    /**
     * 查询系统租户角色列表
     */
    @PreAuthorize("@ss.hasPermi('tenant:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTenantRole sysTenantRole)
    {
        startPage();
        List<SysTenantRole> list = sysTenantRoleService.queryList(sysTenantRole);
        return getDataTable(list);
    }

    /**
     * 获取系统租户角色详细信息
     */
    @PreAuthorize("@ss.hasPermi('tenant:role:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysTenantRoleService.getInfo(id));
    }

    /**
     * 新增系统租户角色
     */
    @PreAuthorize("@ss.hasPermi('tenant:role:add')")
    @Log(title = "系统租户角色", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysTenantRole sysTenantRole)
    {
        if (sysTenantRoleService.hasName(sysTenantRole) > 0){
            return AjaxResult.error("新增角色'" + sysTenantRole.getName() + "'失败，名称已占用");
        }
        return toAjax(sysTenantRoleService.saveRole(sysTenantRole));
    }

    /**
     * 修改系统租户角色
     */
    @PreAuthorize("@ss.hasPermi('tenant:role:edit')")
    @Log(title = "系统租户角色", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysTenantRole sysTenantRole)
    {
        if (sysTenantRoleService.hasName(sysTenantRole) > 0){
            return AjaxResult.error("修改角色'" + sysTenantRole.getName() + "'失败，名称已占用");
        }
        return toAjax(sysTenantRoleService.updateRole(sysTenantRole));
    }

    /**
     * 删除系统租户角色
     */
    @PreAuthorize("@ss.hasPermi('tenant:role:delete')")
    @Log(title = "系统租户角色", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        if(sysTenantService.queryByRoleId(id) > 0){
            return AjaxResult.error("角色已被使用，不能删除");
        }
        return toAjax(sysTenantRoleService.removeById(id));
    }

    @GetMapping("/selectAll")
    public AjaxResult selectAll(SysTenantRole sysTenantRole)
    {
        sysTenantRole.setStatus("0");
        List<SysTenantRole> list = sysTenantRoleService.queryList(sysTenantRole);
        return AjaxResult.success(list);
    }

    /**
     * 禁用启用
     */
    @PreAuthorize("@ss.hasPermi('tenant:role:edit')")
    @Log(title = "系统租户角色", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody SysTenantRole sysTenantRole)
    {
        SysTenantRole role = new SysTenantRole();
        role.setId(sysTenantRole.getId());
        role.setStatus(sysTenantRole.getStatus());
        return toAjax(sysTenantRoleService.updateById(role));
    }

}
