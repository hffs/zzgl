package com.ruoyi.project.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * 系统租户对象 sys_tenant
 *
 * @author wfq
 * @date 2020-02-08
 */
public class SysTenant
{

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long tenantId;

    /** 租户编码 */
    private String tenantNo;

    /** 租户名称 */
    private String tenantName;

    /** 租户类型 */
    private Integer tenantType;

    /**
     * 租户角色
     */
    private Long roleId;

    /**
     * 租户角色
     */
    @TableField(exist = false)
    private String roleName;

    /** 联系人 */
    private String contact;

    /** 联系电话 */
    private String phone;

    /** 联系地址 */
    private String address;

    /** 状态(0正常1停用2删除) */
    private String status;

    /** 过期时间 */
    private Date dueTime;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 备注信息 */
    private String remark;

    @TableField(exist = false)
    private String password;

    /**
     * 租户子类型（1=企业2=评审机构）
     */
    @TableField(exist = false)
    private Integer childrenType;

    @TableField(exist = false)
    private String childrenData;

    public SysTenant() {
    }

    public SysTenant(String tenantNo) {
        this.tenantNo = tenantNo;
    }

    public void setTenantNo(String tenantNo)
    {
        this.tenantNo = tenantNo;
    }

    public String getTenantNo()
    {
        return tenantNo;
    }
    public void setTenantName(String tenantName)
    {
        this.tenantName = tenantName;
    }

    public String getTenantName()
    {
        return tenantName;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDueTime(Date dueTime)
    {
        this.dueTime = dueTime;
    }

    public Date getDueTime()
    {
        return dueTime;
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
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }

    public Integer getTenantType() {
        return tenantType;
    }

    public void setTenantType(Integer tenantType) {
        this.tenantType = tenantType;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
