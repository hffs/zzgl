package com.ruoyi.project.onlineReview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.onlineReview.domain.EvaluationTable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 */
public interface EvaluationTableMapper extends BaseMapper<EvaluationTable> {

    @Select("SELECT\n" +
            "\ta.*, b.id AS bid,\n" +
            "\tb.`status` AS `status`,\n" +
            "\tb.evaluation_result AS evaluationResult\n" +
            "FROM\n" +
            "\tevaluation_table a\n" +
            "LEFT JOIN invitation_evaluation b ON a.id = b.eid\n" +
            "AND b.aid = #{aid}\n")
    List<EvaluationTable> evaluationTableList(@Param("aid") Long aid);
}
