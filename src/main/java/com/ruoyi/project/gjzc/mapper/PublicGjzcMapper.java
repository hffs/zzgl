package com.ruoyi.project.gjzc.mapper;

import com.ruoyi.project.gjzc.domain.PublicGjzc;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 国家政策Mapper接口
 *
 * @author hffs
 * @date 2020-02-18
 */
public interface PublicGjzcMapper
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
     * 删除国家政策
     *
     * @param id 国家政策ID
     * @return 结果
     */
    public int deletePublicGjzcById(Long id);

    /**
     * 批量删除国家政策
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePublicGjzcByIds(Long[] ids);


    /**
     * 关于各个省份的国家政策统计
     * @return
     */
    @Select("SELECT COUNT(a.policyProvince) AS count,a.policyProvince AS policyProvince FROM public_gjzc a GROUP BY a.policyProvince")
    List<PublicGjzc> getCount();

    /**
     * 关于政策类型的统计
     * @return
     */
    @Select("SELECT COUNT(a.policyTypeName) AS count,a.policyTypeName AS policyTypeName FROM public_gjzc a GROUP BY a.policyTypeName")
    List<PublicGjzc> getCountBytype();
}
