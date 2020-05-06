package com.ruoyi.project.zz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zz.domain.ZzPersCert;
import com.ruoyi.project.zz.service.IZzPersCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 个人资质Controller
 * 
 * @author wfq
 * @date 2019-12-20
 */
@RestController
@RequestMapping("/zz/pers/cert")
public class ZzPersCertController extends BaseController
{
    @Autowired
    private IZzPersCertService zzPersCertService;

    /**
     * 查询个人资质列表
     */
    @PreAuthorize("@ss.hasPermi('zz:pers:cert:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZzPersCert zzPersCert)
    {
        startPage();
        QueryWrapper<ZzPersCert> queryWrapper = new QueryWrapper<ZzPersCert>(zzPersCert);
        List<ZzPersCert> list = zzPersCertService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出个人资质列表
     */
    @PreAuthorize("@ss.hasPermi('zz:pers:cert:export')")
    @Log(title = "个人资质", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZzPersCert zzPersCert)
    {
        QueryWrapper<ZzPersCert> queryWrapper = new QueryWrapper<ZzPersCert>(zzPersCert);
        List<ZzPersCert> list = zzPersCertService.list(queryWrapper);
        ExcelUtil<ZzPersCert> util = new ExcelUtil<ZzPersCert>(ZzPersCert.class);
        return util.exportExcel(list, "cert");
    }

    /**
     * 获取个人资质详细信息
     */
    @PreAuthorize("@ss.hasPermi('zz:pers:cert:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {

        return AjaxResult.success(zzPersCertService.getById(id));
    }

    /**
     * 新增个人资质
     */
    @PreAuthorize("@ss.hasPermi('zz:pers:cert:add')")
    @Log(title = "个人资质", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZzPersCert zzPersCert)
    {
        return toAjax(zzPersCertService.save(zzPersCert)? 1 : 0);
    }

    /**
     * 修改个人资质
     */
    @PreAuthorize("@ss.hasPermi('zz:pers:cert:edit')")
    @Log(title = "个人资质", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZzPersCert zzPersCert)
    {
        return toAjax(zzPersCertService.updateById(zzPersCert)? 1 : 0);
    }

    /**
     * 删除个人资质
     */
    @PreAuthorize("@ss.hasPermi('zz:pers:cert:remove')")
    @Log(title = "个人资质", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zzPersCertService.removeByIds(Arrays.asList(ids))?1:0);
    }
}
