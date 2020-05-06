package com.ruoyi.project.onlineReview.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.onlineReview.domain.InvitationAssessment;
import com.ruoyi.project.onlineReview.mapper.InvitationAssessmentMapper;
import com.ruoyi.project.onlineReview.service.IInvitationAssessmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 邀约评审Service业务层处理
 *
 * @author hffs
 * @date 2020-03-03
 */
@Service
public class InvitationAssessmentServiceImpl implements IInvitationAssessmentService
{
    @Resource
    private InvitationAssessmentMapper invitationAssessmentMapper;

    /**
     * 查询邀约评审
     *
     * @param id 邀约评审ID
     * @return 邀约评审
     */
    @Override
    public InvitationAssessment selectInvitationAssessmentById(Long id)
    {
        return invitationAssessmentMapper.selectInvitationAssessmentById(id);
    }

    /**
     * 查询邀约评审列表
     *
     * @param invitationAssessment 邀约评审
     * @return 邀约评审
     */
    @Override
    public List<InvitationAssessment> selectInvitationAssessmentList(InvitationAssessment invitationAssessment)
    {
        return invitationAssessmentMapper.selectInvitationAssessmentList(invitationAssessment);
    }

    /**
     * 新增邀约评审
     *
     * @param invitationAssessment 邀约评审
     * @return 结果
     */
    @Override
    public int insertInvitationAssessment(InvitationAssessment invitationAssessment)
    {
        invitationAssessment.setCreateTime(DateUtils.getNowDate());
        return invitationAssessmentMapper.insertInvitationAssessment(invitationAssessment);
    }

    /**
     * 修改邀约评审
     *
     * @param invitationAssessment 邀约评审
     * @return 结果
     */
    @Override
    public int updateInvitationAssessment(InvitationAssessment invitationAssessment)
    {
        return invitationAssessmentMapper.updateInvitationAssessment(invitationAssessment);
    }

    /**
     * 批量删除邀约评审
     *
     * @param ids 需要删除的邀约评审ID
     * @return 结果
     */
    @Override
    public int deleteInvitationAssessmentByIds(Long[] ids)
    {
        return invitationAssessmentMapper.deleteInvitationAssessmentByIds(ids);
    }

    /**
     * 删除邀约评审信息
     *
     * @param id 邀约评审ID
     * @return 结果
     */
    @Override
    public int deleteInvitationAssessmentById(Long id)
    {
        return invitationAssessmentMapper.deleteInvitationAssessmentById(id);
    }

    @Override
    public List<InvitationAssessment> selectByJgInvitationAssessmentList(InvitationAssessment invitationAssessment) {
        return invitationAssessmentMapper.selectByJgInvitationAssessmentList(invitationAssessment);
    }
}
