package com.dock.lw.common;

public enum ResultCode {

    SUCCESS(200, "操作成功"),

    /****系统错误************************/
    SYSTEM_INNER_ERROR(500, "系统内部错误"),

    INTERFACE_OUTER_INVOKE_ERROR(501, "系统外部接口调用异常"),

    SERVICE_UNAVAILABLE(502, "服务不可用"),

    /****业务错误************************/
    BUSINESS_ERROR(600, "业务错误"),

    RESOURCES_NOT_FOUND(601, "资源没有找到"),

    /****参数错误************************/
    USERID_NOT_NULL(1000, "用户ID不能为空"),

    ACCOUNT_NOT_NULL(1001, "帐号不能为空"),

    PASSWORD_NOT_NULL(1002, "密码不能为空"),

    /****用户错误************************/
    USER_NOT_EXIST(2000, "用户不存在"),

    USER_NOT_LOGIN(2001, "用户没有登录"),

    USER_LOGIN_FAIL(2002, "用户名或密码错误"),

    PERMISSION_NO_ACCESS(4000, "没有访问权限"),

    /****文件上传************************/
    UPLOAD_FAIL(5000, "上传失败"),


    ORG_ID_NOT_FOUND(6000, "orgid参数缺失"),

    ORG_NOT_EXIST(60001, "组织不存在"),

    ;

    private Integer code;

    private String msg;

    private ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
