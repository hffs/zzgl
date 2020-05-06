package com.ruoyi.project.onlineReview.controller;

import com.ruoyi.common.utils.TenantUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.onlineReview.domain.InvitationAssessment;
import com.ruoyi.project.onlineReview.domain.InvitationHistry;
import com.ruoyi.project.onlineReview.service.IInvitationAssessmentService;
import com.ruoyi.project.onlineReview.service.InvitationHistryService;
import com.ruoyi.project.system.domain.SysTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 邀约评审Controller
 *
 * @author hffs
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/assessment/assessment")
public class InvitationAssessmentController extends BaseController
{
    @Autowired
    private IInvitationAssessmentService invitationAssessmentService;
    @Autowired
    private InvitationHistryService invitationHistryService;

    private final String NEWAPPLICATION = "1";//新申请
    private final String FEEDBCAKALREADY = "2";//已反馈
    private final String REAPPLY = "3";//再次申请
    private final String FEEDBACKAGAIN = "4";//再次反馈
    /**
     * 查询邀约评审列表
     */
    @PreAuthorize("@ss.hasPermi('assessment:assessment:list')")
    @GetMapping("/list")
    public TableDataInfo list(InvitationAssessment invitationAssessment)
    {
        SysTenant sysTenant = TenantUtil.getTenant();
        if(sysTenant.getChildrenType()==2){
            invitationAssessment.setTest(sysTenant.getChildrenData().split(","));
            startPage();
            List<InvitationAssessment> list = invitationAssessmentService.selectByJgInvitationAssessmentList(invitationAssessment);
            return getDataTable(list);
        }else {
            startPage();
            System.out.println(invitationAssessment.toString());
            List<InvitationAssessment> list = invitationAssessmentService.selectInvitationAssessmentList(invitationAssessment);
            return getDataTable(list);
        }
    }



    /**
     * 导出邀约评审列表
     */
    @PreAuthorize("@ss.hasPermi('assessment:assessment:export')")
    @Log(title = "邀约评审", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(InvitationAssessment invitationAssessment)
    {
        List<InvitationAssessment> list = invitationAssessmentService.selectInvitationAssessmentList(invitationAssessment);
        ExcelUtil<InvitationAssessment> util = new ExcelUtil<InvitationAssessment>(InvitationAssessment.class);
        return util.exportExcel(list, "assessment");
    }

    /**
     * 获取邀约评审详细信息
     */
    @PreAuthorize("@ss.hasPermi('assessment:assessment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(invitationAssessmentService.selectInvitationAssessmentById(id));
    }

    /**
     * 新增邀约评审
     */
    @PreAuthorize("@ss.hasPermi('assessment:assessment:add')")
    @Log(title = "邀约评审", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InvitationAssessment invitationAssessment)
    {

        invitationAssessment.setCorporateName(TenantUtil.getTenant().getTenantName());
        invitationAssessment.setCurrentState(NEWAPPLICATION);
        invitationAssessment.setSubmitter(getUserName());
        int status = invitationAssessmentService.insertInvitationAssessment(invitationAssessment);
        System.out.println("---"+invitationAssessment.getId());
        /**插入历史记录*/
        InvitationHistry invitationHistry = new InvitationHistry();
        invitationHistry.setInvitationId(invitationAssessment.getId());
        invitationHistry.setRecord(getHistry("企业用户",TenantUtil.getTenant().getTenantName(),NEWAPPLICATION));
        System.out.println(invitationHistry.getRecord());
        invitationHistryService.addHistry(invitationHistry);
        return toAjax(status);
    }

    private String getHistry(String type, String uname,String status){
       StringBuilder sb = new StringBuilder();
       switch (status){
           case "1":
               status = "新申请";
               break;
           case "2":
               status = "反馈";
               break;
           case "3":
               status = "修改申请";
               break;
           case "4":
               status = "再次反馈";
               break;
           default:
               break;
       }
       sb.append(type).append("：").append(uname).append("提交了").append(status);
       return sb.toString();
    }

    /**
     * 修改邀约评审
     */
    @PreAuthorize("@ss.hasPermi('assessment:assessment:edit')")
    @Log(title = "邀约评审", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InvitationAssessment invitationAssessment)
    {
        TenantUtil.getTenant().setTenantId(invitationAssessment.getTenantId());
        InvitationHistry invitationHistry = new InvitationHistry();
        String record ;
        if(invitationAssessment.getAuditTime()!=null){
             record = getHistry("评审老师",TenantUtil.getTenant().getTenantName(),FEEDBCAKALREADY);
            invitationAssessment.setCurrentState(FEEDBCAKALREADY);
        }else{
            invitationAssessment.setCurrentState(NEWAPPLICATION);
            record = getHistry("企业用户",TenantUtil.getTenant().getTenantName(),REAPPLY);
        }
        invitationHistry.setRecord(record);
        invitationHistry.setInvitationId(invitationAssessment.getId());
        invitationAssessment.setApprover(TenantUtil.getTenant().getTenantName());
        invitationHistryService.addHistry(invitationHistry);
        return toAjax(invitationAssessmentService.updateInvitationAssessment(invitationAssessment));
    }

    /**
     * 删除邀约评审
     */
    @PreAuthorize("@ss.hasPermi('assessment:assessment:remove')")
    @Log(title = "邀约评审", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(invitationAssessmentService.deleteInvitationAssessmentByIds(ids));
    }

    @GetMapping("/getHistry")
    public AjaxResult getHistryByinvitationId(@RequestParam(value = "invitationId" ,required = false) Long invitationId){
        System.out.println("invitationId"+invitationId);
        List<InvitationHistry> list =  invitationHistryService.listByInvitationId(invitationId);
        return AjaxResult.success(InvitationHistry.getHistry(list) );
    }
    @GetMapping("/judgementExists")
    public AjaxResult judgementExists(@RequestParam(value = "auditType",required = false)String auditType){
        InvitationAssessment invitationAssessment = new InvitationAssessment();
        invitationAssessment.setAuditType(auditType);
        List<InvitationAssessment> list = invitationAssessmentService.selectInvitationAssessmentList(invitationAssessment);
        String information = null;
        if(list.size()>0){
            information = "当前类型的申请表已经提交，请勿重复提交";
        }
        return AjaxResult.success(information);
    }
}
