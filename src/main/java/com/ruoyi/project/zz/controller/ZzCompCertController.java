package com.ruoyi.project.zz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zz.domain.ZzCompCert;
import com.ruoyi.project.zz.service.IZzCompCertService;
import com.ruoyi.project.zz.service.IZzCompFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业资质Controller
 * 
 * @author wfq
 * @date 2019-12-19
 */
@RestController
@RequestMapping("/zz/comp/cert")
public class ZzCompCertController extends BaseController
{
    @Autowired
    private IZzCompCertService zzCompCertService;
    @Autowired
    private IZzCompFilesService zzCompFilesService;

    /**
     * 查询企业资质列表
     */
    @PreAuthorize("@ss.hasPermi('zz:comp:cert:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZzCompCert zzCompCert)
    {
        startPage();
        QueryWrapper<ZzCompCert> queryWrapper = new QueryWrapper<ZzCompCert>(zzCompCert);
        List<ZzCompCert> list = zzCompCertService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出企业资质列表
     */
    @PreAuthorize("@ss.hasPermi('zz:comp:cert:export')")
    @Log(title = "企业资质", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZzCompCert zzCompCert)
    {
        QueryWrapper<ZzCompCert> queryWrapper = new QueryWrapper<ZzCompCert>(zzCompCert);
        List<ZzCompCert> list = zzCompCertService.list(queryWrapper);
        ExcelUtil<ZzCompCert> util = new ExcelUtil<ZzCompCert>(ZzCompCert.class);
        return util.exportExcel(list, "cert");
    }

    /**
     * 获取企业资质详细信息
     */
    @PreAuthorize("@ss.hasPermi('zz:comp:cert:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(zzCompCertService.getById(id));
    }

    /**
     * 新增企业资质
     */
    @PreAuthorize("@ss.hasPermi('zz:comp:cert:add')")
    @Log(title = "企业资质", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZzCompCert zzCompCert)
    {
        Map<String,Object> param = new HashMap<>();
        param.put("name",zzCompCert.getName());
        if(zzCompCertService.listByMap(param).size() > 0){
            return AjaxResult.error("资质名称已经存在");
        }
        return toAjax(zzCompCertService.save(zzCompCert)? 1 : 0);
    }

    /**
     * 修改企业资质
     */
    @PreAuthorize("@ss.hasPermi('zz:comp:cert:edit')")
    @Log(title = "企业资质", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZzCompCert zzCompCert)
    {
        return toAjax(zzCompCertService.updateById(zzCompCert)? 1 : 0);
    }

    /**
     * 删除企业资质
     */
    @PreAuthorize("@ss.hasPermi('zz:comp:cert:remove')")
    @Log(title = "企业资质", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        //删除资质对应的文件
        zzCompFilesService.deleteByProjectId(ids[0]);//因为前端不存在多选的情况
        return toAjax(zzCompCertService.removeByIds(Arrays.asList(ids))?1:0);
    }
}
