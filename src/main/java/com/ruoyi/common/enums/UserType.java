package com.ruoyi.common.enums;

/**
 * 用户类型
 * 
 * @author ruoyi
 */
public enum UserType
{
    /**
     * 管理员
     */
    ADMIN(0, "管理员"),

    /**
     * 用户
     */
    USER(1, "用户");

    private final Integer code;
    private final String name;

    UserType(Integer code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }
}
