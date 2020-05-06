package com.ruoyi.project.zz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zz.domain.ZzCompFiles;
import com.ruoyi.project.zz.service.IZzCompFilesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 企业资质附件Controller
 *
 * @author wfq
 * @date 2019-12-30
 */
@RestController
@RequestMapping("/zz/files")
public class ZzCompFilesController<digui> extends BaseController
{
    @Autowired
    private IZzCompFilesService zzCompFilesService;


    /**
     * 查询企业资质附件列表
     */
    @PreAuthorize("@ss.hasPermi('zz:files:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZzCompFiles zzCompFiles)
    {
        if(StringUtils.isNotEmpty(zzCompFiles.getFileName())){
            zzCompFiles.setParentId(null);
        }
        List<ZzCompFiles> list = zzCompFilesService.selectFilesList(zzCompFiles);

        return getDataTable(list);
    }

    /**
     * 导出企业资质附件列表
     */
    @PreAuthorize("@ss.hasPermi('zz:files:export')")
    @Log(title = "企业资质附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZzCompFiles zzCompFiles)
    {
        QueryWrapper<ZzCompFiles> queryWrapper = new QueryWrapper<ZzCompFiles>(zzCompFiles);
        List<ZzCompFiles> list = zzCompFilesService.list(queryWrapper);
        ExcelUtil<ZzCompFiles> util = new ExcelUtil<ZzCompFiles>(ZzCompFiles.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 获取企业资质附件详细信息
     */
    @PreAuthorize("@ss.hasPermi('zz:files:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        ZzCompFiles zzCompFiles = new ZzCompFiles();
        zzCompFiles.setId(id);
        return AjaxResult.success(zzCompFilesService.selectFilesList(zzCompFiles).get(0));
    }

    /**
     * 新增企业资质附件
     */
    @PreAuthorize("@ss.hasPermi('zz:files:add')")
    @Log(title = "企业资质附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZzCompFiles zzCompFiles)
    {
        System.out.println(zzCompFiles.toString());
        // 判断是否为文件上传，如果为添加文件，则对文件进行拆分入库
        if(!StringUtils.isEmpty(zzCompFiles.getFilePath())){//上传文件
            JSONArray jsonArr = JSONArray.parseArray(zzCompFiles.getFilePath());
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject obj = (JSONObject) jsonArr.get(i);
                String name = obj.getString("name");
                int index = name.lastIndexOf(".");
                String fileName = name.substring(0,index);
                String fileType = name.substring(index+1,name.length());
                String url = obj.getString("url");
                zzCompFiles.setFilePath(url);
                zzCompFiles.setFileName(fileName);
                zzCompFiles.setFileType(fileType);
                zzCompFilesService.insertFile(zzCompFiles);
            }
        }else{
            zzCompFiles.setFileType("文件夹");
            zzCompFilesService.insertFile(zzCompFiles);
        }
        return toAjax(1);
    }

    /**
     * 修改企业资质附件
     */
    @PreAuthorize("@ss.hasPermi('zz:files:edit')")
    @Log(title = "企业资质附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZzCompFiles zzCompFiles)
    {
        if(zzCompFiles.getId() == zzCompFiles.getParentId()){
            return AjaxResult.error("目录调整不合规，不能将自身文件夹作为上级文件夹");
        }
        ZzCompFiles zzCompFiles2 = new ZzCompFiles();
        Long parentId = zzCompFiles.getParentId();
        if(parentId != 0){//调整后不是一级目录，校验目录的合规性
            zzCompFiles2.setId(parentId);
            String ancestors = zzCompFilesService.selectFilesList(zzCompFiles2).get(0).getAncestors();
            if(ancestors.indexOf(zzCompFiles.getId() + "") > -1){
                return AjaxResult.error("目录调整不合规，不能将子文件夹作为上级文件夹");
            }
        }

        return toAjax(zzCompFilesService.updateFile(zzCompFiles));
    }
    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/upload")
    public AjaxResult avatar(@RequestParam("file") MultipartFile file,@RequestParam("parentId") String parentId) throws IOException
    {
        ZzCompFiles zzCompFiles = new ZzCompFiles();
        zzCompFiles.setId(Long.parseLong(parentId));
        if (!file.isEmpty())
        {
            int ss = zzCompFilesService.selectFilesList(zzCompFiles).size();
            String loderpath = null;
            if(ss>0){
                loderpath = zzCompFilesService.selectFilesList(zzCompFiles).get(0).getFilePath();
            }else{
                loderpath = RuoYiConfig.getAvatarPath();
            }
            String avatar = FileUploadUtils.upload( loderpath, file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", avatar);
            ajax.put("name", file.getOriginalFilename());
            return ajax;
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }
    /**
     * 删除企业资质附件
     */
    @PreAuthorize("@ss.hasPermi('zz:files:remove')")
    @Log(title = "企业资质附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        List<Long> idss =  Arrays.asList(ids);
        if(idss.size()>0){
            for (Long id:idss
                 ) {
                ZzCompFiles zzCompFiles = zzCompFilesService.selectFileById(id);
                if(zzCompFiles.getFileType().equals("文件夹")){
                    Long[] idsss =  zzCompFilesService.findInSetAncestors(zzCompFiles.getId());
                    if(idsss.length>0){
                        zzCompFilesService.removeByIds(Arrays.asList(idsss));
                    }else {
                        zzCompFilesService.removeById(zzCompFiles.getId());
                    }
                }else {
                    zzCompFilesService.removeById(zzCompFiles.getId());
                }
            }
        }


        return toAjax(0);
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping(value="/treeselect/{treeselect}")
    public AjaxResult treeselect(@PathVariable("treeselect") Long  projectId)
    {
        System.out.println("projectId======================"+projectId);
        ZzCompFiles zzCompFiles1 = new ZzCompFiles ();
        zzCompFiles1.setFileType("文件夹");
        zzCompFiles1.setProjectId(projectId);
        List<ZzCompFiles> files = zzCompFilesService.selectFilesList(zzCompFiles1);
        return AjaxResult.success(zzCompFilesService.buildFileTreeSelect(files));
    }
    //该方法未完成，已经封禁
    @Log(title = "文件夹上传", businessType = BusinessType.UPDATE)
    @PostMapping("/uploader")
    public AjaxResult uploader(HttpServletRequest request) throws IOException
    {
        //System.out.println("你妈炸了");
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        Long parentId = Long.parseLong(request.getParameter("parentId"));
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        StringBuilder sb = new StringBuilder();
       if(parentId==0){
           System.out.println("判断当前父ID是否为最高节点");
       }else{
           ZzCompFiles zzCompFiles1 = new ZzCompFiles ();
           zzCompFiles1.setId(parentId);
           ZzCompFiles zz = zzCompFilesService.selectFilesList(zzCompFiles1).get(0);
           if(zz.getParentId()==0){
            System.out.println("当前节点1"+zz.getFileName());
                if (!zz.getFilePath().endsWith("/")){
                    sb.append("/");
                }
               sb.append(zz.getFileName()).append("/");
           }else{
               String parid[] = zz.getAncestors().split(",");
               for (int i = 1; i < parid.length; i++) {
                   System.out.println("Long.parseLong(parid[i]1)"+Long.parseLong(parid[i]));
                   zzCompFiles1.setId(Long.parseLong(parid[i]));
                   ZzCompFiles zzc2 = zzCompFilesService.selectFilesList(zzCompFiles1).get(0);
                   System.out.println("当前节点"+zzc2.getFileName());
                   sb.append("/").append(zzc2.getFileName()).append("/");
               }
               if (!zz.getFilePath().endsWith("/")){
                   sb.append("/");
               }
               sb.append(zz.getFileName()).append("/");
           }
       }
        System.out.println(sb.toString());
        System.out.println(parentId+""+projectId);
        List<MultipartFile> files = params.getFiles("files");

        for (MultipartFile m:files) {

            String s[] = m.getOriginalFilename().split("/");
            String path = m.getOriginalFilename().replace(s[s.length-1],"");
            System.out.println(m.getOriginalFilename());
            for (int i = 0; i <s.length-1 ; i++) {
                StringBuilder sb3  = new StringBuilder();
                sb3.append("/");
                for (int j = 0; j <i+1 ; j++) {
                    sb3.append(s[j]).append("/");
                }
                System.out.println(sb3.toString());
                ZzCompFiles zz = new ZzCompFiles();
                zz.setFileName(s[i]);
                zz.setFileType("文件夹");
                zz.setParentId(parentId);
                zz.setProjectId(projectId);
                zz.setFilePath(RuoYiConfig.getAvatarPath()+sb3.toString());
                zzCompFilesService.insertFile(zz);
            }

            FileUploadUtils.upload(RuoYiConfig.getAvatarPath()+ "/" + sb, m);
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }
    public static void creeatDir(File file){
        if(!file.exists()&&!file.isDirectory()) {
                file.mkdirs();
            }
    }
    public static void delFile(File file){
if (file.exists()) {
if(file.isDirectory()){
File[] files = file.listFiles();
for(int i=0;i<files.length;i++){
delFile(files[i]);
}
System.out.println(file.getAbsolutePath()+"  is deleted");
file.delete();}else {
System.out.println(file.getAbsolutePath()+"  is deleted");
file.delete();}
}else {
System.out.println(file.getAbsolutePath()+" 不存在无法删除");
}
}


}
