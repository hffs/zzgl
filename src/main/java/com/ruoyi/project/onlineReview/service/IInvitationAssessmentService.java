package com.ruoyi.project.onlineReview.service;

import com.ruoyi.project.onlineReview.domain.InvitationAssessment;
import java.util.List;

/**
 * 邀约评审Service接口
 *
 * @author hffs
 * @date 2020-03-03
 */
public interface IInvitationAssessmentService
{
    /**
     * 查询邀约评审
     *
     * @param id 邀约评审ID
     * @return 邀约评审
     */
    public InvitationAssessment selectInvitationAssessmentById(Long id);

    /**
     * 查询邀约评审列表
     *
     * @param invitationAssessment 邀约评审
     * @return 邀约评审集合
     */
    public List<InvitationAssessment> selectInvitationAssessmentList(InvitationAssessment invitationAssessment);

    public List<InvitationAssessment> selectByJgInvitationAssessmentList(InvitationAssessment invitationAssessment);
    /**
     * 新增邀约评审
     *
     * @param invitationAssessment 邀约评审
     * @return 结果
     */
    public int insertInvitationAssessment(InvitationAssessment invitationAssessment);

    /**
     * 修改邀约评审
     *
     * @param invitationAssessment 邀约评审
     * @return 结果
     */
    public int updateInvitationAssessment(InvitationAssessment invitationAssessment);

    /**
     * 批量删除邀约评审
     *
     * @param ids 需要删除的邀约评审ID
     * @return 结果
     */
    public int deleteInvitationAssessmentByIds(Long[] ids);

    /**
     * 删除邀约评审信息
     *
     * @param id 邀约评审ID
     * @return 结果
     */
    public int deleteInvitationAssessmentById(Long id);
}
