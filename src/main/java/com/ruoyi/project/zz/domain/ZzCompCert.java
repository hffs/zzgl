package com.ruoyi.project.zz.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 企业资质对象 zz_comp_cert
 *
 * @author wfq
 * @date 2019-12-19
 */
public class ZzCompCert
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 资质名称 */
    @Excel(name = "资质名称")
    private String name;

    /** 资质有效期 */
    @Excel(name = "资质有效期")
    private String startEndDate;

    /** 审核频次 */
    @Excel(name = "审核频次")
    private String verifyFreq;

    /** 资质类型 */
    @Excel(name = "资质类型")
    private String type;

    /** 提前提醒月数 */
    @Excel(name = "提前提醒月数")
    private String remindMonth;

    /** 资质介绍 */
    @Excel(name = "资质介绍")
    private String introduce;

    /** 资质照片 */
    @Excel(name = "资质照片")
    private String picUrl;

    /** 附件 */
    @Excel(name = "附件")
    private String files;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setStartEndDate(String startEndDate)
    {
        this.startEndDate = startEndDate;
    }

    public String getStartEndDate()
    {
        return startEndDate;
    }
    public void setVerifyFreq(String verifyFreq)
    {
        this.verifyFreq = verifyFreq;
    }

    public String getVerifyFreq()
    {
        return verifyFreq;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setRemindMonth(String remindMonth)
    {
        this.remindMonth = remindMonth;
    }

    public String getRemindMonth()
    {
        return remindMonth;
    }
    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public String getIntroduce()
    {
        return introduce;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }
    public void setFiles(String files)
    {
        this.files = files;
    }

    public String getFiles()
    {
        return files;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("startEndDate", getStartEndDate())
            .append("verifyFreq", getVerifyFreq())
            .append("type", getType())
            .append("remindMonth", getRemindMonth())
            .append("introduce", getIntroduce())
            .append("picUrl", getPicUrl())
            .append("files", getFiles())
            .toString();
    }
}
