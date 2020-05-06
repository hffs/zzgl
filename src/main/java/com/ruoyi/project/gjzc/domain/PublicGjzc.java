package com.ruoyi.project.gjzc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 国家政策对象 public_gjzc
 *
 * @author hffs
 * @date 2020-02-18
 */
public class PublicGjzc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policytype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policytypename;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date policydate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policycity;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policyprovince;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policylinkurl;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policysource;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policyname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long policyno;

    public void setId(Long id)
    {
        this.id = id;
    }

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId()
    {
        return id;
    }
    public void setPolicytype(String policytype)
    {
        this.policytype = policytype;
    }

    public String getPolicytype()
    {
        return policytype;
    }
    public void setPolicytypename(String policytypename)
    {
        this.policytypename = policytypename;
    }

    public String getPolicytypename()
    {
        return policytypename;
    }
    public void setPolicydate(Date policydate)
    {
        this.policydate = policydate;
    }

    public Date getPolicydate()
    {
        return policydate;
    }
    public void setPolicycity(String policycity)
    {
        this.policycity = policycity;
    }

    public String getPolicycity()
    {
        return policycity;
    }
    public void setPolicyprovince(String policyprovince)
    {
        this.policyprovince = policyprovince;
    }

    public String getPolicyprovince()
    {
        return policyprovince;
    }
    public void setPolicylinkurl(String policylinkurl)
    {
        this.policylinkurl = policylinkurl;
    }

    public String getPolicylinkurl()
    {
        return policylinkurl;
    }
    public void setPolicysource(String policysource)
    {
        this.policysource = policysource;
    }

    public String getPolicysource()
    {
        return policysource;
    }
    public void setPolicyname(String policyname)
    {
        this.policyname = policyname;
    }

    public String getPolicyname()
    {
        return policyname;
    }
    public void setPolicyno(Long policyno)
    {
        this.policyno = policyno;
    }

    public Long getPolicyno()
    {
        return policyno;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("policytype", getPolicytype())
            .append("policytypename", getPolicytypename())
            .append("policydate", getPolicydate())
            .append("policycity", getPolicycity())
            .append("policyprovince", getPolicyprovince())
            .append("policylinkurl", getPolicylinkurl())
            .append("policysource", getPolicysource())
            .append("policyname", getPolicyname())
            .append("policyno", getPolicyno())
            .toString();
    }
}
