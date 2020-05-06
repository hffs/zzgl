package com.ruoyi.project.zztp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.zztp.domain.ZztpDetails;

import java.util.List;

/**
 * 资质详情Service接口
 *
 * @author hffs
 * @date 2020-01-07
 */
public interface IZztpDetailsService extends IService<ZztpDetails>
{

    List<ZztpDetails> getCount();
}
