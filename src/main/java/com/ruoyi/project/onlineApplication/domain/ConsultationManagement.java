package com.ruoyi.project.onlineApplication.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 咨询管理对象 consultation_management
 *
 * @author hffs
 * @date 2020-03-18
 */
public class ConsultationManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键自增ID */
    private Long id;

    /** 咨询名称 */
    @Excel(name = "咨询名称")
    private String qualificationName;

    /** 咨询类别 */
    @Excel(name = "咨询类别")
    private String qualificationEntrance;
    @Excel(name = "申请企业")
    private String applicant;
    /** 模板数据 */
    @Excel(name = "模板数据")
    private String templateData;

    /** 模板渲染数据 */
    @Excel(name = "模板渲染数据")
    private String managementData;

    /** 租户ID */
    @Excel(name = "租户ID")
    private Long tenantId;

    @TableField(exist = false)
    private String[] qualificationEntranceBack;

    private String applicationFeedback;

    public void setId(Long id)
    {
        this.id = id;
    }

    private String status;

    @TableField(exist = false)
    private String []test;

    public Long getId()
    {
        return id;
    }
    public void setQualificationName(String qualificationName)
    {
        this.qualificationName = qualificationName;
    }

    public String getQualificationName()
    {
        return qualificationName;
    }
    public void setQualificationEntrance(String qualificationEntrance)
    {
        this.qualificationEntrance = qualificationEntrance;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getQualificationEntrance()
    {
        return qualificationEntrance;
    }
    public void setTemplateData(String templateData)
    {
        this.templateData = templateData;
    }

    public String getTemplateData()
    {
        return templateData;
    }
    public void setManagementData(String managementData)
    {
        this.managementData = managementData;
    }

    public String getManagementData()
    {
        return managementData;
    }
    public void setTenantId(Long tenantId)
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId()
    {
        return tenantId;
    }

    public String[] getQualificationEntranceBack() {
        return qualificationEntranceBack;
    }

    public void setQualificationEntranceBack(String[] qualificationEntranceBack) {
        this.qualificationEntranceBack = qualificationEntranceBack;
    }

    public void setApplicationFeedback(String applicationFeedback) {
        this.applicationFeedback = applicationFeedback;
    }

    public String getApplicationFeedback() {
        return applicationFeedback;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTest(String[] test) {
        this.test = test;
    }

    public String[] getTest() {
        return test;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("qualificationName", getQualificationName())
            .append("createTime", getCreateTime())
            .append("qualificationEntrance", getQualificationEntrance())
            .append("templateData", getTemplateData())
            .append("managementData", getManagementData())
            .append("tenantId", getTenantId())
                .append("applicationFeedback", getApplicationFeedback())
            .toString();
    }
}
