package com.ruoyi.project.onlineReview.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.onlineReview.domain.InvitationCatalog;
import com.ruoyi.project.onlineReview.domain.InvitationFile;
import com.ruoyi.project.onlineReview.mapper.InvitationCatalogMapper;
import com.ruoyi.project.onlineReview.mapper.InvitationFileMapper;
import com.ruoyi.project.onlineReview.service.InvitationCatalogService;
import com.ruoyi.project.onlineReview.service.InvitationFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvitationFileServiceImpl extends ServiceImpl<InvitationFileMapper, InvitationFile> implements InvitationFileService {
    @Resource
    private InvitationFileMapper invitationFileMapper;

    @Override
    public void editStatus(InvitationFile invitationFile) {
        invitationFileMapper.editStatus(invitationFile);
    }


    @Override
    public List<InvitationFile> selectInvitationFileList(InvitationFile invitationFile) {
        return invitationFileMapper.selectInvitationFileList(invitationFile);
    }
}
