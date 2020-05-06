package com.ruoyi.project.zztp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zztp.domain.ZztpAtlas;
import com.ruoyi.project.zztp.domain.ZztpDetails;
import com.ruoyi.project.zztp.domain.ZztpSubclass;
import com.ruoyi.project.zztp.service.IZztpAtlasService;
import com.ruoyi.project.zztp.service.IZztpDetailsService;
import com.ruoyi.project.zztp.service.IZztpSubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 资质详情Controller
 * 
 * @author hffs
 * @date 2020-01-07
 */
@RestController
@RequestMapping("/zztp/details")
public class ZztpDetailsController extends BaseController
{
    @Autowired
    private IZztpDetailsService zztpDetailsService;

    @Autowired
    private IZztpAtlasService zztpAtlasService;

    @Autowired
    private IZztpSubclassService zztpSubclassService;

    /**
     * 查询资质详情列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ZztpDetails zztpDetails)
    {
        startPage();
        System.out.println("zztpDetails.getSid()"+zztpDetails.getSid());
        QueryWrapper<ZztpDetails> queryWrapper = new QueryWrapper<ZztpDetails>(zztpDetails);
        List<ZztpDetails> list = zztpDetailsService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出资质详情列表
     */
    @PreAuthorize("@ss.hasPermi('zztp:details:export')")
    @Log(title = "资质详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZztpDetails zztpDetails)
    {
        QueryWrapper<ZztpDetails> queryWrapper = new QueryWrapper<ZztpDetails>(zztpDetails);
        List<ZztpDetails> list = zztpDetailsService.list(queryWrapper);
        ExcelUtil<ZztpDetails> util = new ExcelUtil<ZztpDetails>(ZztpDetails.class);
        return util.exportExcel(list, "details");
    }

    /**
     * 获取资质详情详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(zztpDetailsService.getById(id));
    }

    /**
     * 新增资质详情
     */
    @PreAuthorize("@ss.hasPermi('zztp:details:add')")
    @Log(title = "资质详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZztpDetails zztpDetails)
    {
        return toAjax(zztpDetailsService.save(zztpDetails)? 1 : 0);
    }

    /**
     * 修改资质详情
     */
    @PreAuthorize("@ss.hasPermi('zztp:details:edit')")
    @Log(title = "资质详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZztpDetails zztpDetails)
    {
        return toAjax(zztpDetailsService.updateById(zztpDetails)? 1 : 0);
    }

    /**
     * 删除资质详情
     */
    @PreAuthorize("@ss.hasPermi('zztp:details:remove')")
    @Log(title = "资质详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zztpDetailsService.removeByIds(Arrays.asList(ids))?1:0);
    }

    @Log(title = "资质table", businessType = BusinessType.DELETE)
    @GetMapping("/getTable")
    public AjaxResult getTable(){
        JSONArray jsonArray = new JSONArray();
        List<ZztpAtlas> list = zztpAtlasService.list();
        if(list.size()>0){
            for (ZztpAtlas zztpAtlas:  list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("img",zztpAtlas.getImage());
                jsonObject.put("imgon",zztpAtlas.getImage());
                jsonObject.put("title",zztpAtlas.getName());
                ZztpSubclass zztpSubclass = new ZztpSubclass();
                zztpSubclass.setAid(zztpAtlas.getId());
                QueryWrapper<ZztpSubclass> queryWrapper = new QueryWrapper<ZztpSubclass>(zztpSubclass);
                List<ZztpSubclass> ll= zztpSubclassService.list(queryWrapper);
                if(ll.size()>0){
                    JSONArray jsonArray2 = new JSONArray();
                    for (ZztpSubclass zztpSub:ll
                         ) {
                        JSONObject jsonObject1 = new JSONObject();
                        JSONObject jsonO = new JSONObject();
                        JSONObject json1 = new JSONObject();
                        json1.put("sid",zztpSub.getId());
                        jsonO.put("path","/zztp/zztp/zzxq/");
                        jsonO.put("query",json1);
                        jsonObject1.put("url",jsonO);
                        jsonObject1.put("img",zztpSub.getImage());
                        jsonObject1.put("title",zztpSub.getName());
                        jsonArray2.add(jsonObject1);
                    }
                    jsonObject.put("mylist",jsonArray2);
                }else{
                    jsonObject.put("mylist",new JSONArray());
                }

                jsonArray.add(jsonObject);
            }
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("swiperdata", jsonArray);
        return ajax;
    }
}
