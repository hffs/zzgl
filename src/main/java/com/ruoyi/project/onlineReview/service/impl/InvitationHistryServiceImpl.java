package com.ruoyi.project.onlineReview.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.onlineReview.domain.InvitationHistry;
import com.ruoyi.project.onlineReview.mapper.InvitationHistryMapper;
import com.ruoyi.project.onlineReview.service.InvitationHistryService;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvitationHistryServiceImpl implements InvitationHistryService {

    @Resource
    private InvitationHistryMapper invitationHistryMapper;

    @Override
    public void addHistry(InvitationHistry invitationHistry) {
        invitationHistry.setCreateTime(DateUtils.getNowDate());
        invitationHistryMapper.addHistry(invitationHistry);
    }
    public List<InvitationHistry> listByInvitationId(@Param("invitationId") Long invitationId){
        return invitationHistryMapper.listByInvitationId(invitationId);
    }

    @Override
    public long addHistryByFileId(InvitationHistry invitationHistry) {
        invitationHistry.setCreateTime(DateUtils.getNowDate());
        invitationHistryMapper.addHistryByFileId(invitationHistry);
        return invitationHistry.getId();
    }
    @Override
    public List<InvitationHistry> listByFileId(@Param("fileId")Long fileId) {
        return invitationHistryMapper.listByFileId(fileId);

    }
}
