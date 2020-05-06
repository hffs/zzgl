package com.ruoyi.project.zztp.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资质详情对象 zztp_details
 *
 * @author hffs
 * @date 2020-01-07
 */
public class ZztpDetails
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long sid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String text;
    private String name;
    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String remark;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Long getId()
    {
        return id;
    }
    public void setSid(Long sid)
    {
        this.sid = sid;
    }

    public Long getSid()
    {
        return sid;
    }
    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
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
            .append("sid", getSid())
            .append("text", getText())
            .append("remark", getRemark())
            .toString();
    }
}
