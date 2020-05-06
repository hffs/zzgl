package com.ruoyi.project.onlineReview.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹目录表 invitation_catalog
 *
 * @author hffs
 */
public class InvitationCatalog
{
    private static final long serialVersionUID = 1L;

    /** 目录ID */
    @TableId
    private Long catalogId;

    /** 父目录ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String catalogName;

    /** 显示顺序 */
    private String orderNum;


    /** 文件状态:0正常,1停用 */
    private String status;

    /** 父文件目录名称 */
    @TableField(exist = false)
    private String parentName;

//    @TableField(exist = false)
    private Long tenantId;

    @TableField(exist = false)
    private String []test;
    /** 子目录 */
    @TableField(exist = false)
    private List<InvitationCatalog> children = new ArrayList<InvitationCatalog>();

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setChildren(List<InvitationCatalog> children) {
        this.children = children;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public Long getParentId() {
        return parentId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAncestors() {
        return ancestors;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public List<InvitationCatalog> getChildren() {
        return children;
    }

    public String getParentName() {
        return parentName;
    }

    public String getStatus() {
        return status;
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
            .append("catalogId", getCatalogId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("catalogName", getCatalogName())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .toString();
    }

}
