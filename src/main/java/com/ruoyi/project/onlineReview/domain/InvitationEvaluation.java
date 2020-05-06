package com.ruoyi.project.onlineReview.domain;

import java.io.Serializable;

/**
 * 评审邀约-评审表-评审结果之间的关联表
 */
public class InvitationEvaluation implements Serializable {

    private Long id,                        //主键ID
            aid,                            //企业申请表Id
            eid;                            //评审表id
    private String status,                  //满足要求状态
            evaluationResult;               //评审结果

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public String getStatus() {
        return status;
    }

    public Long getAid() {
        return aid;
    }

    public Long getEid() {
        return eid;
    }

    public String getEidStr() {
        return String.valueOf(eid);
    }

    public Long getId() {
        return id;
    }

    public String getEvaluationResult() {
        return evaluationResult;
    }
}
