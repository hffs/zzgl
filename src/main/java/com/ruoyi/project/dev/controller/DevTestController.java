package com.ruoyi.project.dev.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.DownloadEnum;
import com.ruoyi.common.utils.DownloadUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dev.domain.DevTest;
import com.ruoyi.project.dev.service.IDevTestService;
import com.ruoyi.project.onlineReview.domain.InvitationEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 开发-测试Controller
 *
 * @author wfq
 * @date 2020-02-24
 */
@RestController
@RequestMapping("/dev/test")
public class DevTestController extends BaseController
{
    @Autowired
    private IDevTestService devTestService;

    /**
     * 查询开发-测试列表
     */
    @GetMapping("/download")
    public void abc() throws IOException {
        String fileName = "abc.docx";
        DownloadEnum downloadEnum = DownloadEnum.ITSS;
        List<InvitationEvaluation > datas = new ArrayList<>();
        for (long i = 1; i < 36; i++) {
            InvitationEvaluation evaluation = new InvitationEvaluation();
            evaluation.setEid(i);
            evaluation.setEvaluationResult("测试" + i);
            long status = i;
            if (status > 4){
                status = 1;
            }
            evaluation.setStatus(String.valueOf(status));
            datas.add(evaluation);
        }
        DownloadUtil.export(fileName, downloadEnum, datas);
    }

    /**
     * 查询开发-测试列表
     */
    @GetMapping("/list")
    public TableDataInfo list(DevTest devTest)
    {
        startPage();
        QueryWrapper<DevTest> queryWrapper = new QueryWrapper<DevTest>(devTest);
        List<DevTest> list = devTestService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 获取开发-测试详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        DevTest devTest = devTestService.getById(id);
        devTest.setMuban("{\"id\": \"主键\",\"name\": \"名称\",\"age\": \"年龄\"}");
        return AjaxResult.success(devTest);
    }

    /**
     * 新增开发-测试
     */
    @Log(title = "开发-测试", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(DevTest devTest)
    {
        return toAjax(devTestService.save(devTest));
    }

    /**
     * 修改开发-测试
     */
    @Log(title = "开发-测试", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody DevTest devTest)
    {
        return toAjax(devTestService.updateById(devTest));
    }


}
