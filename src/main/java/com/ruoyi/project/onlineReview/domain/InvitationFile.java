package com.ruoyi.project.onlineReview.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class InvitationFile implements Serializable {

    @TableId
    private Long fileId;
    private Long
            catalogId,tenantId;
    private String fileName,fileUrl,status;

    private String record;

    @TableField(exist = false)
    private String [] test;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getRecord() {
        return record;
    }

    public String getStatus() {
        return status;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public Long getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getTest() {
        return test;
    }

    public void setTest(String[] test) {
        this.test = test;
    }
}
