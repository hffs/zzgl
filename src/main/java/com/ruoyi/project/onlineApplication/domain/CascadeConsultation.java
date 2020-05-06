package com.ruoyi.project.onlineApplication.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 *
 * 在线咨询入口级联表
 */
@TableName("cascade_consultation")
public class CascadeConsultation implements Serializable {
    private Long id;                                    //主键ID

    @TableField
    private String cascadeConsultation;                 //级联json

    public Long getId() {
        return id;
    }

    public String getCascadeConsultation() {
        return cascadeConsultation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCascadeConsultation(String cascadeConsultation) {
        this.cascadeConsultation = cascadeConsultation;
    }
}
