package com.ruoyi.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.SysDept;

import java.util.List;

/**
 * 部门管理 服务层
 * 
 * @author ruoyi
 */
public interface ISysDeptService extends IService<SysDept>
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<SysDept> selectDeptList(SysDept dept);

    /**
     * 构建前端所需要树结构
     * 
     * @param depts 部门列表
     * @return 树结构列表
     */
    List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * 是否存在部门子节点
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    boolean hasChildByDeptId(Long deptId);

    /**
     * 查询子部门id
     *
     * @param deptId 部门列表
     * @return 树结构列表
     */
    List<Long> queryChildId(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    String checkDeptNameUnique(SysDept dept);

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    boolean insertDept(SysDept dept);

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(SysDept dept);

}
