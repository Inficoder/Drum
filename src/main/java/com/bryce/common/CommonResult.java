package com.bryce.common;

public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected CommonResult() {
    }

    protected CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功获取数据
     */
    //返回成功,无数据,无特殊message
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCode.SUCCESS.getResultCode(), ResultCode.SUCCESS.getResultMessage(), null);
    }

    //返回成功,有数据,无特殊message
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getResultCode(), ResultCode.SUCCESS.getResultMessage(), data);
    }

    //返回成功,无数据,有特殊message
    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getResultCode(), message, null);
    }

    //返回成功,有数据,有message
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getResultCode(), message, data);
    }

    //返回错误,无message
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.INTERNAL_SERVER_ERROR);
    }

    //返回错误
    public static <T> CommonResult<T> failed(ErrorCode errorCode) {
        return new CommonResult<>(errorCode.getResultCode(), errorCode.getResultMessage(), null);
    }

    //返回错误,有message
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.INTERNAL_SERVER_ERROR.getResultCode(), message, null);
    }
}
