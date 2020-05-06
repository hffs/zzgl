package com.ruoyi.project.zz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.zz.domain.ZzPersCert;

import java.util.List;

/**
 * 个人资质Service接口
 *
 * @author wfq
 * @date 2019-12-20
 */
public interface IZzPersCertService extends IService<ZzPersCert>
{

    List<ZzPersCert> countByname();
}
