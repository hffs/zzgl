package com.ruoyi.project.reviewFormElements.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评审表-合同信息
 * @author 14878
 * @Date 2020年3月26日11:37:02
 */
public class ReviewSystemFile implements Serializable {
    private Long
            id,                             //自增主键ID
            reviewId;                       //关联表ID
    private String
            fileAbsolutePath,               //文件全路径
            fileType,                       //文件类型
            fileName;                       //文件名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileAbsolutePath(String fileAbsolutePath) {
        this.fileAbsolutePath = fileAbsolutePath;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileAbsolutePath() {
        return fileAbsolutePath;
    }

    public String getFileType() {
        return fileType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
