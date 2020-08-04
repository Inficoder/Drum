package com.bryce.common;

public class BizException extends RuntimeException {
    protected String errorCode;
    protected String errorMsg;

    public BizException() {
        super();
    }

    /**
     * 基类异常的构造器
     *
     * @param iErrorCode
     */

    public BizException(ErrorCode iErrorCode) {
        super(iErrorCode.getResultCode());
        this.errorCode = iErrorCode.getResultCode();
        this.errorMsg = iErrorCode.getResultMessage();
    }


    /**
     * @return
     * @Description //TODO
     * @Author Bryce
     * @Date 9:34 2020/7/16
     * @Param [iErrorCode, cause]
     **/
    public BizException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getResultCode(), cause);
        this.errorCode = errorCode.getResultCode();
        this.errorMsg = errorCode.getResultMessage();
    }

    /**
     * msg
     *
     * @param errorMsg
     */
    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    /**
     * code msg
     *
     * @param errorCode
     * @param errorMsg
     */
    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * @param errorCode
     * @param errorMsg
     * @param cause
     */
    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
