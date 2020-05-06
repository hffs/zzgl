package com.ruoyi.project.zztp.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资质小类对象 zztp_subclass
 * 
 * @author hffs
 * @date 2020-01-06
 */
public class ZztpSubclass
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long aid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String image;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String remark;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAid(Long aid) 
    {
        this.aid = aid;
    }

    public Long getAid() 
    {
        return aid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setRemark(String remark) 
    {
        this.remark = remark;
    }

    public String getRemark() 
    {
        return remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("aid", getAid())
            .append("name", getName())
            .append("image", getImage())
            .append("remark", getRemark())
            .toString();
    }
}
