package com.ruoyi.project.onlineReview.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.TenantUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.onlineReview.domain.InvitationCatalog;
import com.ruoyi.project.onlineReview.service.InvitationCatalogService;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/assessment/catalog")
public class InvitationCatalogController extends BaseController
{
    @Autowired
    private InvitationCatalogService invitationCatalogService;
    @Autowired
    private ISysUserService userService;
    /**
     * 获取部门列表
     */
    @PreAuthorize("@ss.hasPermi('assessment:catalog:list')")
    @GetMapping("/list")
    public AjaxResult list(InvitationCatalog dept)
    {
        List<InvitationCatalog> depts = invitationCatalogService.selectInvitationCatalogList(dept);
        return AjaxResult.success(invitationCatalogService.buildInvitationCatalogTree(depts));
    }

    /**
     * 根据部门编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('assessment:catalog:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId)
    {
        return AjaxResult.success(invitationCatalogService.getById(deptId));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(InvitationCatalog dept)
    {

        List<InvitationCatalog> depts = invitationCatalogService.selectInvitationCatalogList(dept);
        return AjaxResult.success(invitationCatalogService.buildInvitationCatalogTreeSelect(depts));
    }

    /**
     * 关于评审机构的目录
     * @param tenantId
     * @return
     */
    @GetMapping("/treeselectbytenantId/{tenantId}")
    public AjaxResult treeselectbytenantId(@PathVariable("tenantId")Long  tenantId)
    {
        InvitationCatalog catalog = new InvitationCatalog();
        TenantUtil.getTenant().setTenantId(tenantId);
        catalog.setTest(TenantUtil.getTenant().getChildrenData().split(","));
        List<InvitationCatalog> depts = invitationCatalogService.selectInvitationCatalogList(catalog);
        return AjaxResult.success(invitationCatalogService.buildInvitationCatalogTreeSelect(depts));
    }



    /**
     * 新增目录
     */
    @PreAuthorize("@ss.hasPermi('assessment:catalog:add')")
    @Log(title = "文件目录管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody InvitationCatalog dept)
    {
        if (UserConstants.NOT_UNIQUE.equals(invitationCatalogService.checkInvitationCatalogNameUnique(dept)))
        {
            return AjaxResult.error("新增目录" + dept.getCatalogName() + "'失败，目录名称已存在");
        }
        return toAjax(invitationCatalogService.insertInvitationCatalog(dept));
    }

    /**
     * 修改目录
     */
    @PreAuthorize("@ss.hasPermi('assessment:catalog:edit')")
    @Log(title = "文件目录管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody InvitationCatalog dept)
    {
        if (UserConstants.NOT_UNIQUE.equals(invitationCatalogService.checkInvitationCatalogNameUnique(dept)))
        {
            return AjaxResult.error("修改目录" + dept.getCatalogName() + "'失败，目录名称已存在");
        }
        else if (dept.getParentId().equals(dept.getCatalogId()))
        {
            return AjaxResult.error("修改目录'" + dept.getCatalogName() + "'失败，上级修改目录不能是自己");
        }
        return toAjax(invitationCatalogService.updateInvitationCatalog(dept));
    }

    /**
     * 删除目录
     */
    @PreAuthorize("@ss.hasPermi('assessment:catalog:remove')")
    @Log(title = "文件目录管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId)
    {
        if (invitationCatalogService.hasChildByInvitationCatalogId(deptId))
        {
            return AjaxResult.error("存在下级目录,不允许删除");
        }
        if (userService.hasDeptUser(deptId))
        {
            return AjaxResult.error("部门存在用户,不允许删除");
        }
        return toAjax(invitationCatalogService.removeById(deptId));
    }
}
