package com.bryce.common;

public enum ResultCode implements ErrorCode {
    SUCCESS("200", "操作成功！"),
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    NOT_FOUND("404", "未找到该资源!"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),
    INTERNAL_SERVER_ERROR("500", "操作失败！"),
    VALIDATE_FAILED("401", "参数检验失败"),
    UNAUTHORIZED("402", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限");

    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getResultCode() {
        return code;
    }

    @Override
    public String getResultMessage() {
        return message;
    }
}
