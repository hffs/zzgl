package com.ruoyi.framework.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.project.onlineReview.domain.InvitationCatalog;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.zz.domain.ZzCompFiles;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 *
 * @author ruoyi
 */
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect()
    {

    }

    public TreeSelect(SysDept dept)
    {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu menu)
    {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(ZzCompFiles zzCompFiles) {
        this.id = zzCompFiles.getId();
        this.label = zzCompFiles.getFileName();
        this.children = zzCompFiles.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    public TreeSelect(InvitationCatalog zzCompFiles) {
        this.id = zzCompFiles.getCatalogId();
        this.label = zzCompFiles.getCatalogName();
        this.children = zzCompFiles.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }
}
