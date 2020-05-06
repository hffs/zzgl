package com.ruoyi.project.onlineReview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.onlineReview.domain.InvitationEvaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvitationEvaluationService extends IService<InvitationEvaluation>{

    List<InvitationEvaluation> selectByAid(@Param("aid") Long aid);
}
