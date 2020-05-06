package com.ruoyi.project.onlineReview.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.ruoyi.project.onlineReview.domain.InvitationFile;

import java.util.List;

public interface InvitationFileService extends IService<InvitationFile> {
    void editStatus(InvitationFile invitationFile);
    List<InvitationFile> selectInvitationFileList(InvitationFile invitationFile);
}
