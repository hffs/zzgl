package com.ruoyi.project.onlineReview.domain;

import java.io.Serializable;

/**
 * 评审表实体类
 */
public class EvaluationTable implements Serializable {

    private Long id,                                                                    //主键ID
            aid;                                                                        //企业申请表关联ID,
    private String type,                                                                //评审表类型
            essentialFactor,                                                            //评审要素
            standardClauseNo,                                                           //标准条款号
            maturityRequirements,                                                       //成熟度要求
            keyIndicators,                                                              //关键指标
            auditPoints;                                                                //审核要点
//    private InvitationEvaluation invitationEvaluation;                                  //评审结果表

    private Long bid;                                                                   //评审结果表的主键
    private String status,                                                              //评审要素状态
            evaluationResult;                                                           //评审结果
    public Long getId() {
        return id;
    }

    public Long getAid() {
        return aid;
    }

    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public String getEvaluationResult() {
        return evaluationResult;
    }

    public String getStatus() {
        return status;
    }

    public Long getBid() {
        return bid;
    }

    public String getType() {
        return type;
    }

    public String getAuditPoints() {
        return auditPoints;
    }

    public String getEssentialFactor() {
        return essentialFactor;
    }

    public String getKeyIndicators() {
        return keyIndicators;
    }

    public String getMaturityRequirements() {
        return maturityRequirements;
    }

    public String getStandardClauseNo() {
        return standardClauseNo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public void setAuditPoints(String auditPoints) {
        this.auditPoints = auditPoints;
    }

    public void setEssentialFactor(String essentialFactor) {
        this.essentialFactor = essentialFactor;
    }

    public void setStandardClauseNo(String standardClauseNo) {
        this.standardClauseNo = standardClauseNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKeyIndicators(String keyIndicators) {
        this.keyIndicators = keyIndicators;
    }

    public void setMaturityRequirements(String maturityRequirements) {
        this.maturityRequirements = maturityRequirements;
    }
}
