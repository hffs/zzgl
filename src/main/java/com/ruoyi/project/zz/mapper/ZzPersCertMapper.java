package com.ruoyi.project.zz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.zz.domain.ZzPersCert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 个人资质Mapper接口
 *
 * @author wfq
 * @date 2019-12-20
 */
public interface ZzPersCertMapper extends BaseMapper<ZzPersCert>
{

    @Select("SELECT COUNT(a.`name`) AS count,a.`name` FROM zz_pers_cert a GROUP BY a.`name`")
    List<ZzPersCert> countByname();
}
