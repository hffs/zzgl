package com.ruoyi.project.onlineApplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.onlineApplication.domain.CascadeConsultation;
import org.apache.ibatis.annotations.Select;


public interface CascadeConsultationMapper extends BaseMapper<CascadeConsultation> {

    @Select("SELECT * FROM cascade_consultation WHERE id = 1")
    CascadeConsultation getById();

}
