package com.ruoyi.project.onlineReview.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.onlineReview.domain.InvitationCatalog;
import com.ruoyi.project.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件夹管理 数据层
 *
 * @author ruoyi
 */
public interface InvitationCatalogService extends IService<InvitationCatalog>
{

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<InvitationCatalog> selectInvitationCatalogList(InvitationCatalog dept);
    List<InvitationCatalog>selectInvitationCatalogListBytenantId(InvitationCatalog invitationCatalog);

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    List<InvitationCatalog> buildInvitationCatalogTree(List<InvitationCatalog> depts);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildInvitationCatalogTreeSelect(List<InvitationCatalog> depts);

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    boolean hasChildByInvitationCatalogId(Long deptId);

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
    String checkInvitationCatalogNameUnique(InvitationCatalog dept);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    boolean insertInvitationCatalog(InvitationCatalog dept);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int updateInvitationCatalog(InvitationCatalog dept);


}
