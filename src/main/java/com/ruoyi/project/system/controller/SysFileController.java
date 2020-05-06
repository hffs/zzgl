package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传处理
 *
 * @author ruoyi
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/system/file")
public class SysFileController extends BaseController
{

    /**
     * 文件上传
     */

    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/upload")
    public AjaxResult avatar(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", avatar);
            ajax.put("name", file.getOriginalFilename());
            return ajax;
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }
}
