package com.ruoyi.project.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 角色表 sys_role
 * 
 * @author ruoyi
 */
public class SysRole
{

    /** 角色ID */
    @TableId
    private Long roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色排序 */
    private String roleSort;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 角色备注 */
    private String remark;

    /** 菜单组 */
    @TableField(exist = false)
    private Long[] menuIds;

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

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
