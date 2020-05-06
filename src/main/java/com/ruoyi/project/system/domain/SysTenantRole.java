package com.ruoyi.project.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 系统租户角色对象 sys_tenant_role
 *
 * @author wfq
 * @date 2020-03-06
 */
public class SysTenantRole
{

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Integer type;
    private Integer childrenType;
    private String name;
    private String remark;
    private String status;
    private String childrenData;
    @TableField(exist = false)
    private Long[] menuIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getChildrenType() {
        return childrenType;
    }

    public void setChildrenType(Integer childrenType) {
        this.childrenType = childrenType;
    }

    public String getChildrenData() {
        return childrenData;
    }

    public void setChildrenData(String childrenData) {
        this.childrenData = childrenData;
    }
}
