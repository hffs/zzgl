package com.ruoyi.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.system.domain.SysPost;

import java.util.List;

/**
 * 岗位信息 服务层
 * 
 * @author ruoyi
 */
public interface ISysPostService extends IService<SysPost>
{
    /**
     * 查询岗位信息集合
     * 
     * @param post 岗位信息
     * @return 岗位列表
     */
    List<SysPost> selectPostList(SysPost post);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPost> selectPostAll();

    /**
     * 校验岗位名称
     * 
     * @param post 岗位信息
     * @return 结果
     */
    String checkPostNameUnique(SysPost post);

    /**
     * 修改保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    int updatePost(SysPost post);
}
