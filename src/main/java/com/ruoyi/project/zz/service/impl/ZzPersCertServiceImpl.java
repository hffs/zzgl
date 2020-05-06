package com.ruoyi.project.zz.service.impl;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.zz.domain.ZzPersCert;
import com.ruoyi.project.zz.mapper.ZzPersCertMapper;
import com.ruoyi.project.zz.service.IZzPersCertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 个人资质Service业务层处理
 *
 * @author wfq
 * @date 2019-12-20
 */
@Service
public class ZzPersCertServiceImpl extends ServiceImpl<ZzPersCertMapper, ZzPersCert> implements IZzPersCertService
{

    @Resource
    ZzPersCertMapper zzPersCertMapper;



    public List<ZzPersCert> countByname(){
        return zzPersCertMapper.countByname();
    }
}
