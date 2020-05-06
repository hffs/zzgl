package com.ruoyi.project.reviewFormElements.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.reviewFormElements.domain.ReviewSystemFile;
import com.ruoyi.project.reviewFormElements.service.ReviewSystemFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@RestController
@RequestMapping("/reviewFormElements/reviewSystemFile")
public class ReviewSystemFileController extends BaseController {

    @Autowired
    private ReviewSystemFileService reviewSystemFileService;
    @GetMapping("/list")
    public AjaxResult reviewReviewContractlist(ReviewSystemFile reviewSystemFile)
    {

        QueryWrapper<ReviewSystemFile> queryWrapper = new QueryWrapper<>(reviewSystemFile);
        return AjaxResult.success(reviewSystemFileService.list(queryWrapper));
    }
    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadAll")
    public AjaxResult uploadAll(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            try {
                unZipFiles(RuoYiConfig.getProfile()+avatar.replace("/profile",""),RuoYiConfig.getAvatarPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return AjaxResult.success();
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }
    private   void unZipFiles(String zipPath, String descDir) throws IOException {
        try {
            File zipFile = new File(zipPath);
            if (!zipFile.exists()) {
                throw new IOException("需解压文件不存在.");
            }
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            List<ReviewSystemFile> list = new ArrayList<>();
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\*", "/");
                String nameType = zipEntryName.substring(zipEntryName.lastIndexOf("/")+1);
                if(nameType!=null&&nameType.length()>1){
                    ReviewSystemFile reviewSystemFile = new ReviewSystemFile();
                    String [] typeName = nameType.split("\\.");
                    reviewSystemFile.setFileName(typeName[0]);
                    reviewSystemFile.setFileType(typeName[1]);
                    reviewSystemFile.setFileAbsolutePath(outPath.replace("\\","/"));
                    reviewSystemFile.setCreateTime(new Date());
                    list.add(reviewSystemFile);
                }
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();

            }
            reviewSystemFileService.saveBatch(list);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
