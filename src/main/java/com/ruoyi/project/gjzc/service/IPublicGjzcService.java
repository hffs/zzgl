package com.ruoyi.project.gjzc.service;

import com.ruoyi.project.gjzc.domain.PublicGjzc;
import java.util.List;

/**
 * 国家政策Service接口
 *
 * @author hffs
 * @date 2020-02-18
 */
public interface IPublicGjzcService
{
    /**
     * 查询国家政策
     *
     * @param id 国家政策ID
     * @return 国家政策
     */
    public PublicGjzc selectPublicGjzcById(Long id);

    /**
     * 查询国家政策列表
     *
     * @param publicGjzc 国家政策
     * @return 国家政策集合
     */
    public List<PublicGjzc> selectPublicGjzcList(PublicGjzc publicGjzc);

    /**
     * 新增国家政策
     *
     * @param publicGjzc 国家政策
     * @return 结果
     */
    public int insertPublicGjzc(PublicGjzc publicGjzc);

    /**
     * 修改国家政策
     *
     * @param publicGjzc 国家政策
     * @return 结果
     */
    public int updatePublicGjzc(PublicGjzc publicGjzc);

    /**
     * 批量删除国家政策
     *
     * @param ids 需要删除的国家政策ID
     * @return 结果
     */
    public int deletePublicGjzcByIds(Long[] ids);

    /**
     * 删除国家政策信息
     *
     * @param id 国家政策ID
     * @return 结果
     */
    public int deletePublicGjzcById(Long id);


    public List<PublicGjzc> getCount();

    List<PublicGjzc> getCountBytype();
}
