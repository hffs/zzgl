package com.ruoyi.project.onlineReview.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.TenantUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.onlineReview.domain.InvitationCatalog;
import com.ruoyi.project.onlineReview.domain.InvitationFile;
import com.ruoyi.project.onlineReview.domain.InvitationHistry;
import com.ruoyi.project.onlineReview.service.InvitationCatalogService;
import com.ruoyi.project.onlineReview.service.InvitationFileService;
import com.ruoyi.project.onlineReview.service.InvitationHistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评审文件Controller
 *
 * @author hffs
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/assessment/invitationFile")
public class InvitationFileController extends BaseController {

    @Autowired
    private InvitationFileService invitationFileService;
    @Autowired
    private InvitationHistryService invitationHistryService;
    @Autowired
    private InvitationCatalogService invitationCatalogService;

    private final String NEWAPPLICATION = "1";//新申请
    private final String FEEDBCAKALREADY = "2";//已反馈
    private final String REAPPLY = "3";//再次申请
    private final String FEEDBACKAGAIN = "4";//再次反馈

    /**
     * 查询邀约评审列表
     */
    @PreAuthorize("@ss.hasPermi('assessment:invitationfile:list')")
    @GetMapping("/list")
    public TableDataInfo list(InvitationFile invitationAssessment) {
        startPage();
        System.out.println(invitationAssessment.getTenantId());
        QueryWrapper<InvitationFile> queryWrapper = new QueryWrapper<>(invitationAssessment);
        List<InvitationFile> list = invitationFileService.list(queryWrapper);

        return getDataTable(list);
    }

    @GetMapping("/selectInvitationFileList")
    public TableDataInfo selectInvitationFileList(InvitationFile invitationAssessment) {

        TenantUtil.getTenant().setTenantId(invitationAssessment.getTenantId());
        if(invitationAssessment.getCatalogId()==null){
            InvitationCatalog catalog = new InvitationCatalog();
            catalog.setTest(TenantUtil.getTenant().getChildrenData().split(","));
            List<InvitationCatalog> depts = invitationCatalogService.selectInvitationCatalogList(catalog);
            if(depts.size()>0){
                invitationAssessment.setCatalogId(depts.get(0).getCatalogId());
                startPage();
                List<InvitationFile> list = invitationFileService.selectInvitationFileList(invitationAssessment);
                if(list.size()>0){
                    return getDataTable(list);
                }
            }
        }
        startPage();
        List<InvitationFile> list = invitationFileService.selectInvitationFileList(invitationAssessment);

        return getDataTable(list);
    }


    /**
     * 新增邀约评审
     */
    @PreAuthorize("@ss.hasPermi('assessment:invitationfile:add')")
    @Log(title = "评审文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InvitationFile invitationAssessment) {

        JSONArray jsonArr = JSONArray.parseArray(invitationAssessment.getFileUrl());
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject obj = (JSONObject) jsonArr.get(i);
            String name = obj.getString("name");
            String url = obj.getString("url");
            invitationAssessment.setFileUrl(url);
            invitationAssessment.setFileName(name);
            invitationAssessment.setStatus("1");
            invitationFileService.save(invitationAssessment);
        }
        return AjaxResult.success(200);
    }

    /**
     * 删除目录
     */
    @PreAuthorize("@ss.hasPermi('assessment:invitationfile:remove')")
    @Log(title = "文件目录管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fileId}")
    public AjaxResult remove(@PathVariable Long fileId)
    {
        InvitationFile invitationAssessment  = new InvitationFile();
        invitationAssessment.setFileId(fileId);
        QueryWrapper<InvitationFile> queryWrapper = new QueryWrapper<>(invitationAssessment);
        return toAjax(invitationFileService.remove(queryWrapper));
    }

    @PreAuthorize("@ss.hasPermi('assessment:invitationfile:edit')")
    @Log(title = "文件目录管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody InvitationFile invitationFile)
    {
        System.out.println(invitationFile.getStatus());
        invitationFile.setStatus("2");
        QueryWrapper<InvitationFile> queryWrapper = new QueryWrapper<>(invitationFile);

        return toAjax(invitationFileService.update(queryWrapper));
    }

    @GetMapping("/getHistryByFileId")
    public AjaxResult getHistryByFileId(@RequestParam(value = "fileId" ,required = false) Long fileId,
                                        @RequestParam(value = "record" ,required = false) String record,
                                        @RequestParam(value = "status" ,required = false) String status){

        if(status.equals("1")){
            InvitationFile invitationFile = new InvitationFile();
            invitationFile.setFileId(fileId);
            invitationFile.setStatus("2");
            invitationFileService.editStatus(invitationFile);
        }
        InvitationHistry invitationHistry = new InvitationHistry();
        StringBuilder sb = new StringBuilder();
        sb.append("评审老师：").append( TenantUtil.getTenant().getTenantName()).append("评语【").append(record).append("】");

        invitationHistry.setRecord(sb.toString());
        invitationHistry.setFileId(fileId);
        long id = invitationHistryService.addHistryByFileId(invitationHistry);
        return AjaxResult.success(InvitationHistry.getHistry(invitationHistry)  );
    }

    /**
     * 初始化加载
     * @param fileId
     * @return
     */
    @GetMapping("/initialization")
    public AjaxResult initialization(@RequestParam(value = "fileId" ,required = false) Long fileId){
        List<InvitationHistry> list =  invitationHistryService.listByFileId(fileId);
        return AjaxResult.success(InvitationHistry.getHistry(list)  );
    }
}
