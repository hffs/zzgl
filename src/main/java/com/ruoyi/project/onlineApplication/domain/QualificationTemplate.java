package com.ruoyi.project.onlineApplication.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资质申请模板对象 qualification_template
 *
 * @author hffs
 * @date 2020-03-16
 */
public class QualificationTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String qualificationName;

    /** 资质申请流程类型 */
    @Excel(name = "资质申请流程类型")

    private String qualificationType;

    /** 咨询入口类型 */
    @Excel(name = "咨询入口类型")
    private String qualificationEntrance;

    /** 组件地址 */
    @Excel(name = "组件地址")
    private String componentAddress;

    /** 路由地址 */
    @Excel(name = "路由地址")
    private String routingAddress;

    /** 模板数据 */
    @Excel(name = "模板数据")
    private String templateData;

    public void setId(Long id)
    {
        this.id = id;
    }

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
    public void setQualificationType(String qualificationType)
    {
        this.qualificationType = qualificationType;
    }

    public String getQualificationType()
    {
        return qualificationType;
    }

    public void setQualificationEntrance(String qualificationEntrance) {
        this.qualificationEntrance = qualificationEntrance;
    }

    public String getQualificationEntrance()
    {

        return qualificationEntrance;
    }
    public void setComponentAddress(String componentAddress)
    {
        this.componentAddress = componentAddress;
    }

    public String getComponentAddress()
    {
        return componentAddress;
    }
    public void setRoutingAddress(String routingAddress)
    {
        this.routingAddress = routingAddress;
    }

    public String getRoutingAddress()
    {
        return routingAddress;
    }
    public void setTemplateData(String templateData)
    {
        this.templateData = templateData;
    }

    public String getTemplateData()
    {
        return templateData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("qualificationName", getQualificationName())
            .append("qualificationType", getQualificationType())
            .append("qualificationEntrance", getQualificationEntrance())
            .append("componentAddress", getComponentAddress())
            .append("routingAddress", getRoutingAddress())
            .append("templateData", getTemplateData())
            .append("remark", getRemark())
            .toString();
    }
}
