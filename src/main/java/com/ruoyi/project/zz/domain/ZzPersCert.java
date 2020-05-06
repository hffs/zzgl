package com.ruoyi.project.zz.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 个人资质对象 zz_pers_cert
 *
 * @author wfq
 * @date 2019-12-20
 */
public class ZzPersCert
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 资质名称 */
    @Excel(name = "资质名称")
    private String name;

    /** 资质有效期 */
    @Excel(name = "资质有效期")
    private String startEndDate;

    /** 资质类型 */
    @Excel(name = "资质类型")
    private String type;

    /** 资质介绍 */
    @Excel(name = "资质介绍")
    private String introduce;

    /** 资质照片 */
    @Excel(name = "资质照片")
    private String picUrl;

    @TableField(exist = false)
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /** 租户id */
    private Long tenantId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
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
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
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
    public void setTenantId(Long tenantId)
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId()
    {
        return tenantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("startEndDate", getStartEndDate())
            .append("type", getType())
            .append("introduce", getIntroduce())
            .append("picUrl", getPicUrl())
            .append("tenantId", getTenantId())
            .toString();
    }
}
