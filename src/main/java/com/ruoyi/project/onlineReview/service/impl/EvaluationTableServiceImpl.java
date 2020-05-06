package com.ruoyi.project.onlineReview.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.onlineReview.domain.EvaluationTable;
import com.ruoyi.project.onlineReview.mapper.EvaluationTableMapper;
import com.ruoyi.project.onlineReview.service.EvaluationTableService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class EvaluationTableServiceImpl extends ServiceImpl<EvaluationTableMapper, EvaluationTable> implements EvaluationTableService {

    @Resource
    private EvaluationTableMapper evaluationTableMapper;
    @Override
    public List<EvaluationTable> evaluationTableList(@Param("aid") Long aid){

        return evaluationTableMapper.evaluationTableList(aid);
    }
}
