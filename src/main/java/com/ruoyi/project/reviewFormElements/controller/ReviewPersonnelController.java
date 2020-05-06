package com.ruoyi.project.reviewFormElements.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.reviewFormElements.domain.ReviewPersonnel;
import com.ruoyi.project.reviewFormElements.service.ReviewPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewFormElements/reviewPersonnel")
public class ReviewPersonnelController extends BaseController {

    @Autowired
    private ReviewPersonnelService personnelService;
    @GetMapping("/list")
    public AjaxResult reviewPersonnellist(ReviewPersonnel ReviewPersonnel)
    {

        QueryWrapper<ReviewPersonnel> queryWrapper = new QueryWrapper<>(ReviewPersonnel);
        return AjaxResult.success(personnelService.list(queryWrapper));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody ReviewPersonnel ReviewPersonnel)
    {
        return toAjax(personnelService.updateById(ReviewPersonnel));
    }
    @PostMapping
    public AjaxResult add(@RequestBody List<ReviewPersonnel>  reviewPersonnels)
    {
        if(reviewPersonnels.size()>0){
           personnelService.saveBatch(reviewPersonnels);
           return AjaxResult.success("保存成功");
        }else {
            return AjaxResult.success("数据尚未保存！");
        }
    }
    @DeleteMapping
    public AjaxResult delete(@RequestBody List<ReviewPersonnel>  reviewPersonnels)
    {
        if(reviewPersonnels.size()>0){
            for (ReviewPersonnel personnel:reviewPersonnels
                 ) {
                personnelService.removeById(personnel);
            }
            return AjaxResult.success("删除成功");
        }else {
            return AjaxResult.success("请正常操作！");
        }

    }


}
