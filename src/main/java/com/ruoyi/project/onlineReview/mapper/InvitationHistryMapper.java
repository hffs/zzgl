package com.ruoyi.project.onlineReview.mapper;

import com.ruoyi.project.onlineReview.domain.InvitationHistry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InvitationHistryMapper {

    @Insert("INSERT INTO `invitation_histry` (`invitation_id`, `record`, `create_time`) VALUES (#{invitationId}, #{record},#{createTime})")
    void addHistry(InvitationHistry invitationHistry);

    @Insert("INSERT INTO `invitation_histry` (`file_id`, `record`, `create_time`) VALUES (#{fileId}, #{record},#{createTime})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    long addHistryByFileId(InvitationHistry invitationHistry);

    @Select("SELECT * FROM `invitation_histry` WHERE invitation_id = #{invitationId}")
    List<InvitationHistry> listByInvitationId(@Param("invitationId") Long invitationId);


    @Select("SELECT * FROM `invitation_histry` WHERE file_id = #{fileId}")
    List<InvitationHistry> listByFileId(@Param("fileId") Long fileId);
}
