package com.ruoyi.project.dev.domain;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 开发-测试对象 dev_test
 * 
 * @author wfq
 * @date 2020-02-24
 */
public class DevTest
{

    @TableField
    private Long id;
    private String descr;
    @TableField(exist = false)
    private String muban;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getMuban() {
        return muban;
    }

    public void setMuban(String muban) {
        this.muban = muban;
    }
}
