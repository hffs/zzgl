package com.ruoyi.common.utils;

public enum DownloadEnum {

    ITSS(1, "template/itss_template.docx", "ITSS咨询");

    private Integer code;
    private String path;
    private String name;

    DownloadEnum(Integer code, String path, String name) {
        this.code = code;
        this.path = path;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
