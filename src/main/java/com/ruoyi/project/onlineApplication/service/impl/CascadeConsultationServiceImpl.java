package com.ruoyi.project.onlineApplication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.onlineApplication.domain.CascadeConsultation;
import com.ruoyi.project.onlineApplication.mapper.CascadeConsultationMapper;
import com.ruoyi.project.onlineApplication.service.CascadeConsultationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CascadeConsultationServiceImpl extends ServiceImpl<CascadeConsultationMapper, CascadeConsultation> implements CascadeConsultationService {

    @Resource
    private CascadeConsultationMapper consultationMapper;
    public CascadeConsultation getById(){
        return consultationMapper.getById();
    }

}
