package com.ruoyi.project.gjzc.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.gjzc.domain.PublicGjzc;
import com.ruoyi.project.gjzc.service.IPublicGjzcService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 国家政策Controller
 * 
 * @author hffs
 * @date 2020-02-18
 */
@RestController
@RequestMapping("/gjzc/gjzc")
public class PublicGjzcController extends BaseController
{
    @Autowired
    private IPublicGjzcService publicGjzcService;

    /**
     * 查询国家政策列表
     */
    //@PreAuthorize("@ss.hasPermi('gjzc:gjzc:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublicGjzc publicGjzc)
    {
        startPage();
        List<PublicGjzc> list = publicGjzcService.selectPublicGjzcList(publicGjzc);
        return getDataTable(list);
    }

    /**
     * 导出国家政策列表
     */
   // @PreAuthorize("@ss.hasPermi('gjzc:gjzc:export')")
    @Log(title = "国家政策", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PublicGjzc publicGjzc)
    {
        List<PublicGjzc> list = publicGjzcService.selectPublicGjzcList(publicGjzc);
        ExcelUtil<PublicGjzc> util = new ExcelUtil<PublicGjzc>(PublicGjzc.class);
        return util.exportExcel(list, "gjzc");
    }

    /**
     * 获取国家政策详细信息
     */
    //@PreAuthorize("@ss.hasPermi('gjzc:gjzc:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(publicGjzcService.selectPublicGjzcById(id));
    }

    /**
     * 新增国家政策
     */
    //@PreAuthorize("@ss.hasPermi('gjzc:gjzc:add')")
    @Log(title = "国家政策", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublicGjzc publicGjzc)
    {
        return toAjax(publicGjzcService.insertPublicGjzc(publicGjzc));
    }

    /**
     * 修改国家政策
     */
    //@PreAuthorize("@ss.hasPermi('gjzc:gjzc:edit')")
    @Log(title = "国家政策", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublicGjzc publicGjzc)
    {
        return toAjax(publicGjzcService.updatePublicGjzc(publicGjzc));
    }

    /**
     * 删除国家政策
     */
    //@PreAuthorize("@ss.hasPermi('gjzc:gjzc:remove')")
    @Log(title = "国家政策", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(publicGjzcService.deletePublicGjzcByIds(ids));
    }
}
