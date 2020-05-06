package com.ruoyi.project.zz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.zz.domain.ZzCompFiles;

import java.util.List;

/**
 * 企业资质附件Service接口
 *
 * @author wfq
 * @date 2019-12-30
 */
public interface IZzCompFilesService extends IService<ZzCompFiles>
{
    /**
     * 查询 文件数据
     *
     * @param zzCompFiles 文件信息
     * @return 部门信息集合
     */
    public List<ZzCompFiles> selectFilesList(ZzCompFiles zzCompFiles);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param files 文件列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildFileTreeSelect(List<ZzCompFiles> files);
    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    public List<ZzCompFiles> buildDeptTree(List<ZzCompFiles> depts);
    /**
     * 根据ID查询所有子文件夹
     *
     * @param id 文件ID
     * @return 部门列表
     */
    public List<ZzCompFiles> selectChildrenFileById(Long id);

    /**
     * 是否存在子文件夹
     *
     * @param id 文件ID
     * @return 结果
     */
    public int hasChildByFileId(Long id);

    public int insertFile(ZzCompFiles zzCompFiles);

    public int updateFile(ZzCompFiles zzCompFiles);

    /**
     * 根据projectId 删除文件
     * @param projectId
     */
    public void deleteByProjectId(Long projectId);

    Long[] findInSetAncestors(Long id) ;

    public ZzCompFiles selectFileById(Long id);
}
