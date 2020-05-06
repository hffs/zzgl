package com.ruoyi.project.reviewFormElements.domain;

import java.io.Serializable;

/**
 * 评审表-合同信息
 * @author 14878
 * @Date 2020年3月26日11:37:02
 */
public class ReviewDevelopment implements Serializable {
    private Long
            id,                             //自增主键ID
            funds,                          //经费预算
            reviewId;                       //关联表ID
    private String
            develpmentName,                 //项目名称
            develpmentType,                 //类型
            starTime,                       //开始时间
            endTime,                        //结束时间
            projectLeader,                  //负责人
            projectBriefIntroduction,       //项目简介
            postponed,                      //是否延期
            team;                           //团队人员

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setDevelpmentName(String develpmentName) {
        this.develpmentName = develpmentName;
    }

    public void setDevelpmentType(String develpmentType) {
        this.develpmentType = develpmentType;
    }

    public void setFunds(Long funds) {
        this.funds = funds;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public void setPostponed(String postponed) {
        this.postponed = postponed;
    }

    public void setProjectBriefIntroduction(String projectBriefIntroduction) {
        this.projectBriefIntroduction = projectBriefIntroduction;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEndTime() {
        return endTime;
    }

    public Long getFunds() {
        return funds;
    }

    public String getDevelpmentName() {
        return develpmentName;
    }

    public String getDevelpmentType() {
        return develpmentType;
    }

    public String getStarTime() {
        return starTime;
    }

    public String getPostponed() {
        return postponed;
    }

    public String getProjectBriefIntroduction() {
        return projectBriefIntroduction;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
