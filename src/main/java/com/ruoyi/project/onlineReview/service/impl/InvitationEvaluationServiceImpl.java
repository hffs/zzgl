package com.ruoyi.project.onlineReview.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.onlineReview.domain.InvitationEvaluation;
import com.ruoyi.project.onlineReview.mapper.InvitationEvaluationMapper;
import com.ruoyi.project.onlineReview.service.InvitationEvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvitationEvaluationServiceImpl extends ServiceImpl<InvitationEvaluationMapper, InvitationEvaluation> implements InvitationEvaluationService {

    @Resource
    private InvitationEvaluationMapper invitationEvaluationMapper;
    @Override
    public List<InvitationEvaluation> selectByAid(Long aid) {
        return invitationEvaluationMapper.selectByAid(aid);
    }
}
