package com.ruoyi.project.onlineReview.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.DownloadEnum;
import com.ruoyi.common.utils.DownloadUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.onlineReview.domain.EvaluationTable;
import com.ruoyi.project.onlineReview.domain.InvitationAssessment;
import com.ruoyi.project.onlineReview.domain.InvitationEvaluation;
import com.ruoyi.project.onlineReview.service.EvaluationTableService;
import com.ruoyi.project.onlineReview.service.IInvitationAssessmentService;
import com.ruoyi.project.onlineReview.service.InvitationEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/assessment/evaluationTable")
public class EvaluationTableController extends BaseController {

    @Autowired
    private EvaluationTableService evaluationTableService;

    @Autowired
    private IInvitationAssessmentService invitationAssessmentService;

    @Autowired
    private InvitationEvaluationService invitationEvaluationService;

    @GetMapping("/evaluationTableList")
    public AjaxResult evaluationTableList(EvaluationTable evaluationTable)
    {
        evaluationTable.setId(null);
        QueryWrapper<EvaluationTable> queryWrapper = new QueryWrapper<>(evaluationTable);
        List<EvaluationTable> list = evaluationTableService.evaluationTableList(evaluationTable.getAid());
        return AjaxResult.success(list);
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody InvitationEvaluation dept)
    {
        if(dept.getId()!=null){
            return toAjax(invitationEvaluationService.updateById(dept));
        }else {
            return toAjax( invitationEvaluationService.save(dept));
        }
    }

    @GetMapping("/downlodepsb")
    public void downlodepsb ( Long aid) throws IOException
    {
        List<InvitationEvaluation>list = invitationEvaluationService.selectByAid(aid);
        InvitationAssessment invitationAssessment = invitationAssessmentService.selectInvitationAssessmentById(aid);
        DownloadUtil.export(invitationAssessment.getCorporateName(), DownloadEnum.ITSS, list);

    }

}
