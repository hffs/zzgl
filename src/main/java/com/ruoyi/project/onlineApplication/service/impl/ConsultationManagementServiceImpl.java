package com.ruoyi.project.onlineApplication.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.onlineApplication.domain.ConsultationManagement;
import com.ruoyi.project.onlineApplication.mapper.ConsultationManagementMapper;
import com.ruoyi.project.onlineApplication.service.IConsultationManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 咨询管理Service业务层处理
 *
 * @author hffs
 * @date 2020-03-18
 */
@Service
public class ConsultationManagementServiceImpl implements IConsultationManagementService
{
    @Resource
    private ConsultationManagementMapper consultationManagementMapper;

    /**
     * 查询咨询管理
     *
     * @param id 咨询管理ID
     * @return 咨询管理
     */
    @Override
    public ConsultationManagement selectConsultationManagementById(Long id)
    {
        return consultationManagementMapper.selectConsultationManagementById(id);
    }

    /**
     * 查询咨询管理列表
     *
     * @param consultationManagement 咨询管理
     * @return 咨询管理
     */
    @Override
    public List<ConsultationManagement> selectConsultationManagementList(ConsultationManagement consultationManagement)
    {
        return consultationManagementMapper.selectConsultationManagementList(consultationManagement);
    }

    /**
     * 新增咨询管理
     *
     * @param consultationManagement 咨询管理
     * @return 结果
     */
    @Override
    public int insertConsultationManagement(ConsultationManagement consultationManagement)
    {
        consultationManagement.setCreateTime(DateUtils.getNowDate());
        return consultationManagementMapper.insertConsultationManagement(consultationManagement);
    }

    /**
     * 修改咨询管理
     *
     * @param consultationManagement 咨询管理
     * @return 结果
     */
    @Override
    public int updateConsultationManagement(ConsultationManagement consultationManagement)
    {
        return consultationManagementMapper.updateConsultationManagement(consultationManagement);
    }

    /**
     * 批量删除咨询管理
     *
     * @param ids 需要删除的咨询管理ID
     * @return 结果
     */
    @Override
    public int deleteConsultationManagementByIds(Long[] ids)
    {
        return consultationManagementMapper.deleteConsultationManagementByIds(ids);
    }

    /**
     * 删除咨询管理信息
     *
     * @param id 咨询管理ID
     * @return 结果
     */
    @Override
    public int deleteConsultationManagementById(Long id)
    {
        return consultationManagementMapper.deleteConsultationManagementById(id);
    }

    @Override
    public List<ConsultationManagement> selectByTeacherConsultationManagementList(ConsultationManagement consultationManagement) {
        return consultationManagementMapper.selectByTeacherConsultationManagementList(consultationManagement);
    }

    @Override
    public ConsultationManagement selectConsultationManagementByQualificationEntrance(String qualificationEntrance) {
        return consultationManagementMapper.selectConsultationManagementByQualificationEntrance(qualificationEntrance);
    }

    @Override
    public int changeStatus(Long id, String status) {
       return consultationManagementMapper.changeStatus(id,status);
    }
}
