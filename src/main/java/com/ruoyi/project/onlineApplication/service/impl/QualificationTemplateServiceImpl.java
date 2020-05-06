package com.ruoyi.project.onlineApplication.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.onlineApplication.mapper.QualificationTemplateMapper;
import com.ruoyi.project.onlineApplication.domain.QualificationTemplate;
import com.ruoyi.project.onlineApplication.service.IQualificationTemplateService;

import javax.annotation.Resource;

/**
 * 资质申请模板Service业务层处理
 *
 * @author hffs
 * @date 2020-03-16
 */
@Service
public class QualificationTemplateServiceImpl implements IQualificationTemplateService
{
    @Resource
    private QualificationTemplateMapper qualificationTemplateMapper;

    /**
     * 查询资质申请模板
     *
     * @param id 资质申请模板ID
     * @return 资质申请模板
     */
    @Override
    public QualificationTemplate selectQualificationTemplateById(Long id)
    {
        return qualificationTemplateMapper.selectQualificationTemplateById(id);
    }

    /**
     * 查询资质申请模板列表
     *
     * @param qualificationTemplate 资质申请模板
     * @return 资质申请模板
     */
    @Override
    public List<QualificationTemplate> selectQualificationTemplateList(QualificationTemplate qualificationTemplate)
    {
        return qualificationTemplateMapper.selectQualificationTemplateList(qualificationTemplate);
    }

    /**
     * 新增资质申请模板
     *
     * @param qualificationTemplate 资质申请模板
     * @return 结果
     */
    @Override
    public int insertQualificationTemplate(QualificationTemplate qualificationTemplate)
    {
        return qualificationTemplateMapper.insertQualificationTemplate(qualificationTemplate);
    }

    /**
     * 修改资质申请模板
     *
     * @param qualificationTemplate 资质申请模板
     * @return 结果
     */
    @Override
    public int updateQualificationTemplate(QualificationTemplate qualificationTemplate)
    {
        return qualificationTemplateMapper.updateQualificationTemplate(qualificationTemplate);
    }

    /**
     * 批量删除资质申请模板
     *
     * @param ids 需要删除的资质申请模板ID
     * @return 结果
     */
    @Override
    public int deleteQualificationTemplateByIds(Long[] ids)
    {
        return qualificationTemplateMapper.deleteQualificationTemplateByIds(ids);
    }

    /**
     * 删除资质申请模板信息
     *
     * @param id 资质申请模板ID
     * @return 结果
     */
    @Override
    public int deleteQualificationTemplateById(Long id)
    {
        return qualificationTemplateMapper.deleteQualificationTemplateById(id);
    }
}
