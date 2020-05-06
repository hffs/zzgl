package com.ruoyi.project.gjzc.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gjzc.mapper.PublicGjzcMapper;
import com.ruoyi.project.gjzc.domain.PublicGjzc;
import com.ruoyi.project.gjzc.service.IPublicGjzcService;

import javax.annotation.Resource;

/**
 * 国家政策Service业务层处理
 *
 * @author hffs
 * @date 2020-02-18
 */
@Service
public class PublicGjzcServiceImpl implements IPublicGjzcService
{
    @Resource
    private PublicGjzcMapper publicGjzcMapper;

    /**
     * 查询国家政策
     *
     * @param id 国家政策ID
     * @return 国家政策
     */
    @Override
    public PublicGjzc selectPublicGjzcById(Long id)
    {
        return publicGjzcMapper.selectPublicGjzcById(id);
    }

    /**
     * 查询国家政策列表
     *
     * @param publicGjzc 国家政策
     * @return 国家政策
     */
    @Override
    public List<PublicGjzc> selectPublicGjzcList(PublicGjzc publicGjzc)
    {
        return publicGjzcMapper.selectPublicGjzcList(publicGjzc);
    }

    /**
     * 新增国家政策
     *
     * @param publicGjzc 国家政策
     * @return 结果
     */
    @Override
    public int insertPublicGjzc(PublicGjzc publicGjzc)
    {
        return publicGjzcMapper.insertPublicGjzc(publicGjzc);
    }

    /**
     * 修改国家政策
     *
     * @param publicGjzc 国家政策
     * @return 结果
     */
    @Override
    public int updatePublicGjzc(PublicGjzc publicGjzc)
    {
        return publicGjzcMapper.updatePublicGjzc(publicGjzc);
    }

    /**
     * 批量删除国家政策
     *
     * @param ids 需要删除的国家政策ID
     * @return 结果
     */
    @Override
    public int deletePublicGjzcByIds(Long[] ids)
    {
        return publicGjzcMapper.deletePublicGjzcByIds(ids);
    }

    /**
     * 删除国家政策信息
     *
     * @param id 国家政策ID
     * @return 结果
     */
    @Override
    public int deletePublicGjzcById(Long id)
    {
        return publicGjzcMapper.deletePublicGjzcById(id);
    }


    public List<PublicGjzc> getCount(){
        return publicGjzcMapper.getCount();
    }

    public List<PublicGjzc> getCountBytype(){
        return publicGjzcMapper.getCountBytype();
    }
}
