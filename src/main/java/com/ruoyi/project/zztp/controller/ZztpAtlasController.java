package com.ruoyi.project.zztp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.zztp.domain.ZztpAtlas;
import com.ruoyi.project.zztp.service.IZztpAtlasService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 资质大类Controller
 * 
 * @author hffs
 * @date 2020-01-06
 */
@RestController
@RequestMapping("/zztp/atlas")
public class ZztpAtlasController extends BaseController
{
    @Autowired
    private IZztpAtlasService zztpAtlasService;

    /**
     * 查询资质大类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ZztpAtlas zztpAtlas)
    {
        startPage();
        QueryWrapper<ZztpAtlas> queryWrapper = new QueryWrapper<ZztpAtlas>(zztpAtlas);
        List<ZztpAtlas> list = zztpAtlasService.list(queryWrapper);
        return getDataTable(list);
    }

    @GetMapping("/alllist")
    public AjaxResult allList()
    {

        List<ZztpAtlas> list = zztpAtlasService.list(null);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("zztpAtlaslist", list);
        return ajax;

    }

    /**
     * 导出资质大类列表
     */
    @PreAuthorize("@ss.hasPermi('zztp:atlas:export')")
    @Log(title = "资质大类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZztpAtlas zztpAtlas)
    {
        QueryWrapper<ZztpAtlas> queryWrapper = new QueryWrapper<ZztpAtlas>(zztpAtlas);
        List<ZztpAtlas> list = zztpAtlasService.list(queryWrapper);
        ExcelUtil<ZztpAtlas> util = new ExcelUtil<ZztpAtlas>(ZztpAtlas.class);
        return util.exportExcel(list, "atlas");
    }

    /**
     * 获取资质大类详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(zztpAtlasService.getById(id));
    }

    /**
     * 新增资质大类
     */
    @PreAuthorize("@ss.hasPermi('zztp:atlas:add')")
    @Log(title = "资质大类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZztpAtlas zztpAtlas)
    {
        return toAjax(zztpAtlasService.save(zztpAtlas)? 1 : 0);
    }

    /**
     * 修改资质大类
     */
    @PreAuthorize("@ss.hasPermi('zztp:atlas:edit')")
    @Log(title = "资质大类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZztpAtlas zztpAtlas)
    {
        return toAjax(zztpAtlasService.updateById(zztpAtlas)? 1 : 0);
    }

    /**
     * 删除资质大类
     */
    @PreAuthorize("@ss.hasPermi('zztp:atlas:remove')")
    @Log(title = "资质大类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zztpAtlasService.removeByIds(Arrays.asList(ids))?1:0);
    }

    @RequestMapping("upload")
    public AjaxResult upload (@RequestParam("file") MultipartFile file) {
        AjaxResult ajax = AjaxResult.success();


        // 获取原始名字
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路径
        String filePath = "d:/upload/";
        // 文件重命名，防止重复
        fileName = filePath + UUID.randomUUID() + fileName;
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            ajax.put("imgUrl", fileName);
            return ajax;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajax;
    }

    @ResponseBody
    @RequestMapping("/uploadCategory")
    public void uploadCategory(HttpServletRequest request,
                               @RequestParam("file") MultipartFile[] file){

        if (file != null && file.length > 0) {
        for (MultipartFile temp : file) {
            //处理上传的文件
            System.out.println(temp.getOriginalFilename());
            //其他逻辑
        }
    }


}
    @RequestMapping(value="/uploadFolder",method=RequestMethod.POST)
    @ResponseBody
    public String uploadFileFolder(HttpServletRequest request) {

        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = params.getFiles("fileFolder");
        System.out.println(files.size());
        return  null;

    }



}
