package com.ruoyi.project.zztp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zztp.domain.ZztpSubclass;
import com.ruoyi.project.zztp.service.IZztpSubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 资质小类Controller
 * 
 * @author hffs
 * @date 2020-01-06
 */
@RestController
@RequestMapping("/zztp/subclass")
public class ZztpSubclassController extends BaseController
{
    @Autowired
    private IZztpSubclassService zztpSubclassService;

    /**
     * 查询资质小类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ZztpSubclass zztpSubclass)
    {
        startPage();
        QueryWrapper<ZztpSubclass> queryWrapper = new QueryWrapper<ZztpSubclass>(zztpSubclass);
        List<ZztpSubclass> list = zztpSubclassService.list(queryWrapper);
        return getDataTable(list);
    }

    @GetMapping("/alllist")
    public AjaxResult allList()
    {
        List<ZztpSubclass> list = zztpSubclassService.list(null);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("zztpSubclasslist", list);
        return ajax;
    }

    /**
     * 导出资质小类列表
     */
    @PreAuthorize("@ss.hasPermi('zztp:subclass:export')")
    @Log(title = "资质小类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZztpSubclass zztpSubclass)
    {
        QueryWrapper<ZztpSubclass> queryWrapper = new QueryWrapper<ZztpSubclass>(zztpSubclass);
        List<ZztpSubclass> list = zztpSubclassService.list(queryWrapper);
        ExcelUtil<ZztpSubclass> util = new ExcelUtil<ZztpSubclass>(ZztpSubclass.class);
        return util.exportExcel(list, "subclass");
    }

    /**
     * 获取资质小类详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(zztpSubclassService.getById(id));
    }

    /**
     * 新增资质小类
     */
    @PreAuthorize("@ss.hasPermi('zztp:subclass:add')")
    @Log(title = "资质小类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZztpSubclass zztpSubclass)
    {
        return toAjax(zztpSubclassService.save(zztpSubclass)? 1 : 0);
    }

    /**
     * 修改资质小类
     */
    @PreAuthorize("@ss.hasPermi('zztp:subclass:edit')")
    @Log(title = "资质小类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZztpSubclass zztpSubclass)
    {
        return toAjax(zztpSubclassService.updateById(zztpSubclass)? 1 : 0);
    }

    /**
     * 删除资质小类
     */
    @PreAuthorize("@ss.hasPermi('zztp:subclass:remove')")
    @Log(title = "资质小类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zztpSubclassService.removeByIds(Arrays.asList(ids))?1:0);
    }
}
