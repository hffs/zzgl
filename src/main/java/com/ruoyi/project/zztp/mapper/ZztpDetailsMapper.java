package com.ruoyi.project.zztp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.zztp.domain.ZztpDetails;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 资质详情Mapper接口
 *
 * @author hffs
 * @date 2020-01-07
 */
public interface ZztpDetailsMapper extends BaseMapper<ZztpDetails>
{

    @Select("SELECT COUNT(a.sid) AS count,b.`name` FROM zztp_details a,zztp_subclass b WHERE a.sid = b.id GROUP BY b.`name`")
    List<ZztpDetails> getCount();
}
