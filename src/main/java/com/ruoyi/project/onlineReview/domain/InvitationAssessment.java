package com.ruoyi.project.onlineReview.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 邀约评审对象 invitation_assessment
 *
 * @author hffs
 * @date 2020-03-03
 */
public class InvitationAssessment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String corporateName;

    /** 审核类型 */
    @Excel(name = "审核类型")
    private String auditType;

    /** 申请表 */
    @Excel(name = "申请表")
    private String applicationUrl;

    /** 提交人 */
    @Excel(name = "提交人")
    private String submitter;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String currentState;

    private String applicationName;
    /** 审核时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")


    private String auditTime;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approver;

    @TableField(exist = false)
    private String []test;
    private Long tenantId;

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCorporateName(String corporateName)
    {
        this.corporateName = corporateName;
    }

    public String getCorporateName()
    {
        return corporateName;
    }
    public void setAuditType(String auditType)
    {
        this.auditType = auditType;
    }

    public String getAuditType()
    {
        return auditType;
    }
    public void setApplicationUrl(String applicationUrl)
    {
        this.applicationUrl = applicationUrl;
    }

    public String getApplicationUrl()
    {
        return applicationUrl;
    }
    public void setSubmitter(String submitter)
    {
        this.submitter = submitter;
    }

    public String getSubmitter()
    {
        return submitter;
    }
    public void setCurrentState(String currentState)
    {
        this.currentState = currentState;
    }

    public String getCurrentState()
    {
        return currentState;
    }
    public void setAuditTime(String auditTime)
    {
        this.auditTime = auditTime;
    }

    public String getAuditTime()
    {
        return auditTime;
    }
    public void setApprover(String approver)
    {
        this.approver = approver;
    }

    public String getApprover()
    {
        return approver;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String[] getTest() {
        return test;
    }

    public void setTest(String[] test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("corporateName", getCorporateName())
            .append("createTime", getCreateTime())
            .append("auditType", getAuditType())
            .append("applicationUrl", getApplicationUrl())
            .append("submitter", getSubmitter())
            .append("currentState", getCurrentState())
            .append("auditTime", getAuditTime())
            .append("approver", getApprover())
            .toString();
    }
}
