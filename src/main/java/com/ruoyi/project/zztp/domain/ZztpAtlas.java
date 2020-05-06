package com.ruoyi.project.zztp.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资质大类对象 zztp_atlas
 * 
 * @author hffs
 * @date 2020-01-06
 */
public class ZztpAtlas
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 资质名称 */
    @Excel(name = "资质名称")
    private String name;
    /** 资质logo图片*/
    @Excel(name = "资质logo图片")
    private String image;
    /** $column.columnComment */
    @Excel(name = "资质备注")
    private String remark;

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
    public void setRemark(String remark) 
    {
        this.remark = remark;
    }
    public String getImage()
    {
        return image;
    }
    public void setImage(String image)
    {
        this.image = image;
    }
    public String getRemark() 
    {
        return remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
                .append("image", getImage())
            .append("remark", getRemark())
            .toString();
    }
}
