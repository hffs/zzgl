package com.ruoyi.project.onlineApplication.mapper;

import com.ruoyi.project.onlineApplication.domain.QualificationTemplate;
import java.util.List;

/**
 * 资质申请模板Mapper接口
 * 
 * @author hffs
 * @date 2020-03-16
 */
public interface QualificationTemplateMapper 
{
    /**
     * 查询资质申请模板
     * 
     * @param id 资质申请模板ID
     * @return 资质申请模板
     */
    public QualificationTemplate selectQualificationTemplateById(Long id);

    /**
     * 查询资质申请模板列表
     * 
     * @param qualificationTemplate 资质申请模板
     * @return 资质申请模板集合
     */
    public List<QualificationTemplate> selectQualificationTemplateList(QualificationTemplate qualificationTemplate);

    /**
     * 新增资质申请模板
     * 
     * @param qualificationTemplate 资质申请模板
     * @return 结果
     */
    public int insertQualificationTemplate(QualificationTemplate qualificationTemplate);

    /**
     * 修改资质申请模板
     * 
     * @param qualificationTemplate 资质申请模板
     * @return 结果
     */
    public int updateQualificationTemplate(QualificationTemplate qualificationTemplate);

    /**
     * 删除资质申请模板
     * 
     * @param id 资质申请模板ID
     * @return 结果
     */
    public int deleteQualificationTemplateById(Long id);

    /**
     * 批量删除资质申请模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQualificationTemplateByIds(Long[] ids);
}
