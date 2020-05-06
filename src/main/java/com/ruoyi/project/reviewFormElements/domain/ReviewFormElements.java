package com.ruoyi.project.reviewFormElements.domain;

import java.io.Serializable;

/**
 * 评审表-元素类型
 * @author 14878
 * @Date 2020年3月26日11:37:02
 */
public class ReviewFormElements implements Serializable {
    private Long id;                        //自增主键ID
    private String  name;                   //元素类型名称

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
