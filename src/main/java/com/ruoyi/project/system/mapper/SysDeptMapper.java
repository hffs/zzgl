package com.ruoyi.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门管理 数据层
 * 
 * @author ruoyi
 */
public interface SysDeptMapper extends BaseMapper<SysDept>
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<SysDept> selectDeptList(SysDept dept);

    /**
     * 根据ID查询所有子部门
     * 
     * @param deptId 部门ID
     * @return 部门列表
     */
    List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * 是否存在子节点
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    int hasChildByDeptId(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(SysDept dept);

    /**
     * 修改所在部门的父级部门状态
     * 
     * @param dept 部门
     */
    void updateDeptStatus(SysDept dept);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 查询子部门id
     *
     * @param deptId 部门列表
     * @return 树结构列表
     */
    List<Long> queryChildId(Long deptId);

}
