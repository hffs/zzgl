package com.ruoyi.project.reviewFormElements.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.reviewFormElements.domain.ReviewContract;
import com.ruoyi.project.reviewFormElements.service.ReviewContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewFormElements/reviewContract")
public class ReviewContractController extends BaseController {

    @Autowired
    private ReviewContractService contractService;
    @GetMapping("/list")
    public AjaxResult reviewReviewContractlist(ReviewContract contract)
    {

        QueryWrapper<ReviewContract> queryWrapper = new QueryWrapper<>(contract);
        return AjaxResult.success(contractService.list(queryWrapper));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody ReviewContract contract)
    {
        return toAjax(contractService.updateById(contract));
    }
    @PostMapping
    public AjaxResult add(@RequestBody List<ReviewContract>  reviewPersonnels)
    {
        if(reviewPersonnels.size()>0){
            contractService.saveBatch(reviewPersonnels);
           return AjaxResult.success("保存成功");
        }else {
            return AjaxResult.success("数据尚未保存！");
        }
    }
    @DeleteMapping
    public AjaxResult delete(@RequestBody List<ReviewContract>  reviewPersonnels)
    {
        if(reviewPersonnels.size()>0){
            for (ReviewContract personnel:reviewPersonnels
                 ) {
                contractService.removeById(personnel);
            }
            return AjaxResult.success("删除成功");
        }else {
            return AjaxResult.success("请正常操作！");
        }

    }
}
