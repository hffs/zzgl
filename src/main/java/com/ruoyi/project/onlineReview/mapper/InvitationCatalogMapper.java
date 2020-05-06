package com.ruoyi.project.onlineReview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.onlineReview.domain.InvitationCatalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件夹管理 数据层
 *
 * @author ruoyi
 */
public interface InvitationCatalogMapper extends BaseMapper<InvitationCatalog>
{
    /**
     * 查询文件夹管理数据
     *
     * @param invitationCatalog 文件夹目录信息
     * @return 部门信息集合
     */
    List<InvitationCatalog> selectInvitationCatalogList(InvitationCatalog invitationCatalog);

    /**
     * 忽略租户ID
     * @param invitationCatalog
     * @return
     */
    List<InvitationCatalog>selectInvitationCatalogListBytenantId(InvitationCatalog invitationCatalog);

    /**
     * 根据ID查询所有子目录
     *
     * @param catalogId 部门ID
     * @return 部门列表
     */
    List<InvitationCatalog> selectChildrenInvitationCatalogById(Long catalogId);

    /**
     * 是否存在子节点
     *
     * @param catalogId 部门ID
     * @return 结果
     */
    int hasChildBycatalogId(Long catalogId);

    /**
     * 校验部门名称是否唯一
     *
     * @param catalogName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    InvitationCatalog checkcatalogNameUnique(@Param("catalogName") String catalogName, @Param("parentId") Long parentId);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int updateInvitationCatalog(InvitationCatalog dept);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
    void updateInvitationCatalogStatus(InvitationCatalog dept);

    /**
     * 修改子元素关系
     *
     * @param invitationCatalogs 子元素
     * @return 结果
     */
    int updateInvitationCatalogChildren(@Param("invitationCatalogs") List<InvitationCatalog> invitationCatalogs);

    /**
     * 查询子部门id
     *
     * @param deptId 部门列表
     * @return 树结构列表
     */
    List<Long> queryChildId(Long deptId);

}
