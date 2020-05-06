package com.ruoyi.project.onlineApplication.mapper;

import com.ruoyi.project.onlineApplication.domain.ConsultationManagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 咨询管理Mapper接口
 *
 * @author hffs
 * @date 2020-03-18
 */
public interface ConsultationManagementMapper
{
    /**
     * 查询咨询管理
     *
     * @param id 咨询管理ID
     * @return 咨询管理
     */
    public ConsultationManagement selectConsultationManagementById(Long id);

    /**
     * 查询咨询管理列表
     *
     * @param consultationManagement 咨询管理
     * @return 咨询管理集合
     */
    public List<ConsultationManagement> selectConsultationManagementList(ConsultationManagement consultationManagement);

    /**
     * 基于咨询老师的去除数据隔离
     * @param consultationManagement
     * @return
     */
    List<ConsultationManagement> selectByTeacherConsultationManagementList(ConsultationManagement consultationManagement);


    /**
     * 新增咨询管理
     *
     * @param consultationManagement 咨询管理
     * @return 结果
     */
    public int insertConsultationManagement(ConsultationManagement consultationManagement);

    /**
     * 修改咨询管理
     *
     * @param consultationManagement 咨询管理
     * @return 结果
     */
    public int updateConsultationManagement(ConsultationManagement consultationManagement);

    /**
     * 删除咨询管理
     *
     * @param id 咨询管理ID
     * @return 结果
     */
    public int deleteConsultationManagementById(Long id);

    /**
     * 批量删除咨询管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConsultationManagementByIds(Long[] ids);


    /**
     *
     * @param qualificationEntrance
     * @return 基于咨询类型查询的实体类
     */
    ConsultationManagement selectConsultationManagementByQualificationEntrance(String qualificationEntrance);


    int changeStatus(@Param("id")Long id,@Param("status")String status);
}
