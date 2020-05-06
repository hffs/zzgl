package com.ruoyi.project.onlineReview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.onlineReview.domain.InvitationEvaluation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface InvitationEvaluationMapper  extends BaseMapper<InvitationEvaluation> {


    @Select("SELECT * FROM invitation_evaluation a WHERE a.aid = #{aid}")
    List<InvitationEvaluation> selectByAid(@Param("aid") Long aid);
}
