package com.ruoyi.project.reviewFormElements.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.reviewFormElements.domain.ReviewContract;
import com.ruoyi.project.reviewFormElements.domain.ReviewDevelopment;
import com.ruoyi.project.reviewFormElements.service.ReviewDevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewFormElements/reviewDevelopment")
public class ReviewDevelopmentController extends BaseController {

    @Autowired
    private ReviewDevelopmentService developmentService;
    @GetMapping("/list")
    public AjaxResult reviewReviewDevelopmentlist(ReviewDevelopment development)
    {

        QueryWrapper<ReviewDevelopment> queryWrapper = new QueryWrapper<>(development);
        return AjaxResult.success(developmentService.list(queryWrapper));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody ReviewDevelopment development)
    {
        return toAjax(developmentService.updateById(development));
    }
    @PostMapping
    public AjaxResult add(@RequestBody List<ReviewDevelopment>  reviewPersonnels)
    {
        if(reviewPersonnels.size()>0){
            developmentService.saveBatch(reviewPersonnels);
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
                developmentService.removeById(personnel);
            }
            return AjaxResult.success("删除成功");
        }else {
            return AjaxResult.success("请正常操作！");
        }

    }
}
