package com.ruoyi.project.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.gjzc.domain.PublicGjzc;
import com.ruoyi.project.gjzc.service.IPublicGjzcService;
import com.ruoyi.project.system.service.ISysTenantService;
import com.ruoyi.project.zz.domain.ZzCompCert;
import com.ruoyi.project.zz.domain.ZzPersCert;
import com.ruoyi.project.zz.service.IZzCompCertService;
import com.ruoyi.project.zz.service.IZzPersCertService;

import com.ruoyi.project.zztp.domain.ZztpDetails;

import com.ruoyi.project.zztp.service.IZztpDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * g关于对首页统计图表进行处理
 */
@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private ISysTenantService sysTenantService;

    @Autowired
    private IPublicGjzcService iPublicGjzcService;

    @Autowired
    private IZzCompCertService zzCompCertService;

    @Autowired
    private IZzPersCertService iZzPersCertService;

    @Autowired
    private IZztpDetailsService iZztpDetailsService;

    @GetMapping("/sysTenantChart")
    public AjaxResult sysTenantChart(){

        int tnormal =  sysTenantService.sysTenantCharTnormal();//租户正常数
        int discontinue = sysTenantService.sysTenantDiscontinue();//租户停用数
        int tatol = tnormal+discontinue;//租户总数

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("租户总数",tatol);
        jsonObject.put("正常数",tnormal);
        jsonObject.put("停用数",discontinue);
        return AjaxResult.success(jsonObject);
    }
    @GetMapping("/gjzcByPolicyprovince")
    public AjaxResult gjzcByPolicyprovince(){

        List<PublicGjzc> list = iPublicGjzcService.getCount();
        JSONArray array = new JSONArray();
        for (PublicGjzc p:list
             ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("省份",p.getPolicyprovince());
            jsonObject.put("数量",p.getCount());
            array.add(jsonObject);
        }
        return AjaxResult.success(array);
    }

    @GetMapping("/gjzcBytype")
    public AjaxResult gjzcBytype(){

        List<PublicGjzc> list = iPublicGjzcService.getCountBytype();
        JSONArray array = new JSONArray();
        for (PublicGjzc p:list
        ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("政策类型",p.getPolicytypename());
            jsonObject.put("数量",p.getCount());
            array.add(jsonObject);
        }
        return AjaxResult.success(array);
    }

    /**
     * 企业资质
     * @return
     */
    @GetMapping("/enterpriseQualification")
    public AjaxResult enterpriseQualification(){
        List<ZzCompCert> list = zzCompCertService.list();
        JSONArray array = new JSONArray();
        for (ZzCompCert z:list
             ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("资质名称",z.getName());

            array.add(jsonObject);
        }
        return AjaxResult.success(array);
    }

    /**
     * 个人资质
     * @return
     */
    @GetMapping("/personalQualification")
    public AjaxResult personalQualification(){
        List<ZzPersCert>list = iZzPersCertService.countByname();
        JSONArray array = new JSONArray();
        for (ZzPersCert z:list
             ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("资质名称",z.getName());
            jsonObject.put("数量",z.getCount());

            array.add(jsonObject);
        }
        return AjaxResult.success(array);
    }

    @GetMapping("/zztpCount")
    public AjaxResult zztpCount(){
        List<ZztpDetails> list= iZztpDetailsService.getCount();

        JSONArray array = new JSONArray();
        for (ZztpDetails z:list
        ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("资质名称",z.getName());
            jsonObject.put("数量",z.getCount());

            array.add(jsonObject);
        }
        return AjaxResult.success(array);
    }

}
