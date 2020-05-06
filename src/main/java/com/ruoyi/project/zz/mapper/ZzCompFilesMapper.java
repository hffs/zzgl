package com.ruoyi.project.zz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.zz.domain.ZzCompFiles;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 企业资质附件Mapper接口
 *
 * @author wfq
 * @date 2019-12-30
 */
@Component
public interface ZzCompFilesMapper extends BaseMapper<ZzCompFiles>
{
    /**
     * 查询 文件数据
     *
     * @param zzCompFiles 文件信息
     * @return 部门信息集合
     */
    public List<ZzCompFiles> selectFilesList(ZzCompFiles zzCompFiles);

    public ZzCompFiles selectFileById(Long id);
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
     * 修改子元素关系
     *
     * @param files 子元素
     * @return 结果
     */
    public int updateFileChildren(@Param("files") List<ZzCompFiles> files);

    /**
     * 根据projectId 删除文件
     * @param projectId
     */
    public void deleteByProjectId(Long projectId);


     Long[] findInSetAncestors(Long id) ;
}
