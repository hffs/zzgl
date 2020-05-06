package com.ruoyi.project.onlineReview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.onlineReview.domain.EvaluationTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface EvaluationTableService extends IService<EvaluationTable> {

    List<EvaluationTable> evaluationTableList(@Param("aid") Long aid);
}
