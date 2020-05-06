package com.ruoyi.project.zztp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.zztp.domain.ZztpDetails;
import com.ruoyi.project.zztp.mapper.ZztpDetailsMapper;
import com.ruoyi.project.zztp.service.IZztpDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资质详情Service业务层处理
 *
 * @author hffs
 * @date 2020-01-07
 */
@Service
public class ZztpDetailsServiceImpl extends ServiceImpl<ZztpDetailsMapper, ZztpDetails> implements IZztpDetailsService
{

    @Resource
    ZztpDetailsMapper zztpDetailsMapper;
    public List<ZztpDetails> getCount(){
        return zztpDetailsMapper.getCount();
    }
}
