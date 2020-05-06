package com.ruoyi.project.onlineReview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.onlineReview.domain.InvitationFile;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface InvitationFileMapper extends BaseMapper<InvitationFile> {

    @Update("UPDATE `invitation_file` SET  `status`=#{status} WHERE (`file_id`=#{fileId}) AND `status` = '1'")
    void editStatus(InvitationFile invitationFile);

    /**
     * 评估老师界面
     * @param invitationFile
     * @return
     */
    List<InvitationFile>selectInvitationFileList(InvitationFile invitationFile);
}
