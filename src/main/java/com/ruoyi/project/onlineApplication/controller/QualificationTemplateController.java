package com.ruoyi.project.onlineApplication.controller;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.onlineApplication.domain.CascadeConsultation;
import com.ruoyi.project.onlineApplication.domain.QualificationTemplate;
import com.ruoyi.project.onlineApplication.service.CascadeConsultationService;
import com.ruoyi.project.onlineApplication.service.IQualificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资质申请模板Controller
 *
 * @author hffs
 * @date 2020-03-16
 */
@RestController
@RequestMapping("/onlineApplication/template")
public class QualificationTemplateController extends BaseController
{
    @Autowired
    private IQualificationTemplateService qualificationTemplateService;

    @Autowired
    private CascadeConsultationService consultationService;

    /**
     * 查询资质申请模板列表
     */
    @PreAuthorize("@ss.hasPermi('onlineApplication:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualificationTemplate qualificationTemplate)
    {
        startPage();
        List<QualificationTemplate> list = qualificationTemplateService.selectQualificationTemplateList(qualificationTemplate);
        return getDataTable(list);
    }

    /**
     * 导出资质申请模板列表
     */
    @PreAuthorize("@ss.hasPermi('onlineApplication:template:export')")
    @Log(title = "资质申请模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(QualificationTemplate qualificationTemplate)
    {
        List<QualificationTemplate> list = qualificationTemplateService.selectQualificationTemplateList(qualificationTemplate);
        ExcelUtil<QualificationTemplate> util = new ExcelUtil<QualificationTemplate>(QualificationTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 获取资质申请模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('onlineApplication:template:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(qualificationTemplateService.selectQualificationTemplateById(id));
    }

    /**
     * 新增资质申请模板
     */
    @PreAuthorize("@ss.hasPermi('onlineApplication:template:add')")
    @Log(title = "资质申请模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualificationTemplate qualificationTemplate)
    {
        System.out.println(qualificationTemplate.getTemplateData());
        return toAjax(qualificationTemplateService.insertQualificationTemplate(qualificationTemplate));
    }

    /**
     * 修改资质申请模板
     */
    @PreAuthorize("@ss.hasPermi('onlineApplication:template:edit')")
    @Log(title = "资质申请模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualificationTemplate qualificationTemplate)
    {
        return toAjax(qualificationTemplateService.updateQualificationTemplate(qualificationTemplate));
    }

    /**
     * 删除资质申请模板
     */
    @PreAuthorize("@ss.hasPermi('onlineApplication:template:remove')")
    @Log(title = "资质申请模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qualificationTemplateService.deleteQualificationTemplateByIds(ids));
    }

    @GetMapping("/CascadeConsultationJson")
    public AjaxResult CascadeConsultationJson(){
        CascadeConsultation consultation = consultationService.getById();
//        JSONObject jsonObject = JSONUtil.parseObj(consultation.getCascadeConsultation());

        return AjaxResult.success("成功",consultation.getCascadeConsultation());
    }
}
