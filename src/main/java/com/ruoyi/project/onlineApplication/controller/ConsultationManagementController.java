package com.ruoyi.project.onlineApplication.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.TenantUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.onlineApplication.domain.ConsultationManagement;
import com.ruoyi.project.onlineApplication.domain.QualificationTemplate;
import com.ruoyi.project.onlineApplication.service.IConsultationManagementService;
import com.ruoyi.project.onlineApplication.service.IQualificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 咨询管理Controller
 *
 * @author hffs
 * @date 2020-03-18
 */
@RestController
@RequestMapping("/consultationManagement/management")
public class ConsultationManagementController extends BaseController
{
    @Autowired
    private IConsultationManagementService consultationManagementService;

    @Autowired
    private IQualificationTemplateService qualificationTemplateService;
    /**
     * 查询咨询管理列表
     */
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConsultationManagement consultationManagement)
    {
        int type =  TenantUtil.getTenant().getChildrenType();
        if(type==2){
            consultationManagement.setStatus("2");//查询限制条件，企业用户提交

            startPage();
            List<ConsultationManagement> list = consultationManagementService.selectByTeacherConsultationManagementList(consultationManagement);
            return getDataTable(list);
        }else {
            startPage();
            List<ConsultationManagement> list = consultationManagementService.selectConsultationManagementList(consultationManagement);
            return getDataTable(list);
        }
    }

    /**
     * 导出咨询管理列表
     */
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:export')")
    @Log(title = "咨询管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ConsultationManagement consultationManagement)
    {
        List<ConsultationManagement> list = consultationManagementService.selectConsultationManagementList(consultationManagement);
        ExcelUtil<ConsultationManagement> util = new ExcelUtil<ConsultationManagement>(ConsultationManagement.class);
        return util.exportExcel(list, "management");
    }

    /**
     * 获取咨询管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(consultationManagementService.selectConsultationManagementById(id));
    }

    /**
     * 新增咨询管理
     */
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:add')")
    @Log(title = "咨询管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConsultationManagement consultationManagement)
    {
//        consultationManagement.setApplicant(getUserName());
        consultationManagement.setApplicant(TenantUtil.getTenant().getTenantName());
        return toAjax(consultationManagementService.insertConsultationManagement(consultationManagement));
    }

    /**
     * 修改咨询管理
     */
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:edit')")
    @Log(title = "咨询管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConsultationManagement consultationManagement)
    {
        return toAjax(consultationManagementService.updateConsultationManagement(consultationManagement));
    }

    /**
     * 修改咨询管理
     */
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:fankui')")
    @PostMapping("/applicationFeedback")
    public AjaxResult applicationFeedback(@RequestBody ConsultationManagement consultationManagement)
    {
        System.out.println(consultationManagement.toString());
        TenantUtil.getTenant().setTenantId(consultationManagement.getTenantId());
        return toAjax(consultationManagementService.updateConsultationManagement(consultationManagement));
    }

    /**
     * 删除咨询管理
     */
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:remove')")
    @Log(title = "咨询管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(consultationManagementService.deleteConsultationManagementByIds(ids));
    }

    /**
     *  基于咨询类型获取模板数据
     * @param consultationManagement
     * @return
     */
    @PostMapping("/getTempAndData")
    public AjaxResult getTempAndData(@RequestBody ConsultationManagement consultationManagement)
    {
       String  qualificationEntrance = Arrays.toString(consultationManagement.getQualificationEntranceBack()).trim().replace(" ","");
       consultationManagement =  consultationManagementService.selectConsultationManagementByQualificationEntrance(qualificationEntrance);
        if(consultationManagement!=null){
            return AjaxResult.success("",consultationManagement);
        }else{
            ConsultationManagement consultationManagement1 = new ConsultationManagement();
            QualificationTemplate qualificationTemp = new QualificationTemplate() ;
            qualificationTemp.setQualificationEntrance(qualificationEntrance);
            List<QualificationTemplate> lsit = qualificationTemplateService.selectQualificationTemplateList(qualificationTemp);
            if(lsit.size()>0){
                JSONArray array = new JSONArray();
                for (QualificationTemplate qualificationTemplate:lsit
                     ) {
                    array.add(qualificationTemplate.getTemplateData());
                }
                consultationManagement1.setTemplateData(array.toJSONString());
            }else{
                return AjaxResult.success("模板数据尚未加载！！！");
            }
            consultationManagement1.setApplicant(TenantUtil.getTenant().getTenantName());
            consultationManagement1.setQualificationEntrance(qualificationEntrance);
            consultationManagementService.insertConsultationManagement(consultationManagement1);
            return AjaxResult.success("",consultationManagementService.selectConsultationManagementByQualificationEntrance(qualificationEntrance));
    }
    }
    @PreAuthorize("@ss.hasPermi('consultationManagement:management:tijiao')")
    @GetMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestParam("id")Long id){
        String status = "2";//企业提交状态
        return AjaxResult.success(consultationManagementService.changeStatus(id,status));
    }

}
