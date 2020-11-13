package com.fh.common;


public enum ServerEnum {
    SUCCESS(200,"操作成功"),
    ERROR(1000,"操作失败"),
    USER_PASSWORD_IS_ERROR(1004,"用户名或密码错误"),
    PASSWORD_IS_ERROR(1003,"密码错误"),
    USER_IS_NOT_EXIST(1006,"用户不存在"),
    USER_IS_EXIST(1005,"用户名已存在"),
    LOGIN_ERROR(1002,"登录失败"),
    PRODUCT_NOT_EXIST(1008,"商品不存在"),
    PRODUCT_IS_DOWN(1009,"商品已下架"),
    PRODUCT_IS_NULL(1020,"商品为空请前往首页添加商品"),
    PARAM_IS_NULL(1030,"参数为空"),
    DATA_IS_NULL(1040,"数据不完整"),
    TOKEN_ERROR(1050,"幂等性验证失败"),
    USER_PASSWORD_IS_NULL(1001,"用户名或密码不能为空");

    private int code;

    private String message;

    ServerEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
