package com.ruoyi.project.reviewFormElements.domain;

import java.io.Serializable;

/**
 * 评审表-合同信息
 * @author 14878
 * @Date 2020年3月26日11:37:02
 */
public class ReviewContract implements Serializable {
    private Long
            id,                             //自增主键ID
            period,                         //合同期限
            reviewId;                       //关联表ID
    private String
            baginTime,                      //开始时间
            described,                       //服务内容
            contractName;                   //合同名称

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Long getPeriod() {
        return period;
    }

    public String getBaginTime() {
        return baginTime;
    }

    public String getDescribed() {
        return described;
    }

    public void setBaginTime(String baginTime) {
        this.baginTime = baginTime;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
