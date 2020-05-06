package com.ruoyi.project.reviewFormElements.domain;

import java.io.Serializable;

/**
 * 评审表-人员信息
 * @author 14878
 * @Date 2020年3月26日11:37:02
 */
public class ReviewPersonnel implements Serializable {
    private Long id,                        //自增主键ID
            workingLife,                    //工作年限
            reviewId;                       //关联表ID
    private String  personName,             //人员姓名
            position,                       //人员职位
            school,                         //学校
            major,                          //专业
            qualificationCertificate,       //人员资质证书
            education;                      //学历
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
    public Long getReviewId() {
        return reviewId;
    }
    public String getPosition() {
        return position;
    }

    public String getEducation() {
        return education;
    }

    public String getMajor() {
        return major;
    }

    public String getSchool() {
        return school;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Long getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(Long workingLife) {
        this.workingLife = workingLife;
    }

    public String getQualificationCertificate() {
        return qualificationCertificate;
    }

    public void setQualificationCertificate(String qualificationCertificate) {
        this.qualificationCertificate = qualificationCertificate;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
