package com.ruoyi.project.onlineReview.service;

import com.ruoyi.project.onlineReview.domain.InvitationHistry;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface InvitationHistryService {

     void addHistry(InvitationHistry invitationHistry);

     List<InvitationHistry> listByInvitationId(@Param("invitationId") Long invitationId);

    long addHistryByFileId(InvitationHistry invitationHistry);

    List<InvitationHistry> listByFileId(@Param("fileId") Long fileId);
}
