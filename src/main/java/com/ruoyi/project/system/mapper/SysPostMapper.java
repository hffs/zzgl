package com.ruoyi.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.system.domain.SysPost;

import java.util.List;

/**
 * 岗位信息 数据层
 * 
 * @author ruoyi
 */
public interface SysPostMapper extends BaseMapper<SysPost>
{
    /**
     * 查询岗位数据集合
     * 
     * @param post 岗位信息
     * @return 岗位数据集合
     */
    List<SysPost> selectPostList(SysPost post);

    /**
     * 修改岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    int updatePost(SysPost post);

    /**
     * 校验岗位名称
     * 
     * @param postName 岗位名称
     * @return 结果
     */
    SysPost checkPostNameUnique(String postName);

}
