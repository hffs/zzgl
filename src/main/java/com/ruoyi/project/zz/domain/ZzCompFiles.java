package com.ruoyi.project.zz.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 企业资质附件对象 zz_comp_files
 * 
 * @author wfq
 * @date 2019-12-30
 */
public class ZzCompFiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /**  项目id（属于哪个企业资质） */
    private Long projectId;

    /** 父id */
    @Excel(name = "父id")
    private Long parentId;

    /** 祖级列表 */
    @Excel(name = "祖级列表")
    private String ancestors;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 大小 */
    @Excel(name = "大小")
    private String size;

    /** $column.columnComment */
    private Date createTime;

    /** $column.columnComment */
    private Date updateTime;

    /** $column.columnComment */
    private String createBy;

    /** $column.columnComment */
    private String updateBy;

    /** 租户id */
    @Excel(name = "租户id")
    private Long tenantId;

    /** 子文件 */
    private List<ZzCompFiles> children = new ArrayList<ZzCompFiles>();

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setAncestors(String ancestors) 
    {
        this.ancestors = ancestors;
    }

    public String getAncestors() 
    {
        return ancestors;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getSize() 
    {
        return size;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }
    public void setCreateBy(String createBy) 
    {
        this.createBy = createBy;
    }

    public String getCreateBy() 
    {
        return createBy;
    }
    public void setUpdateBy(String updateBy) 
    {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() 
    {
        return updateBy;
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
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("fileName", getFileName())
            .append("fileType", getFileType())
            .append("filePath", getFilePath())
            .append("size", getSize())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("tenantId", getTenantId())
            .toString();
    }

    public List<ZzCompFiles> getChildren() {
        return children;
    }

    public void setChildren(List<ZzCompFiles> children) {
        this.children = children;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
