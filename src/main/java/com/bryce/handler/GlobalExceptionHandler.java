package com.bryce.handler;

import com.bryce.common.BizException;
import com.bryce.common.CommonResult;
import com.bryce.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 运行时异常
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public CommonResult bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return CommonResult.failed(ResultCode.INTERNAL_SERVER_ERROR);
    }
    /**
     * shiro登录
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public CommonResult authenticationExceptionHandler(HttpServletRequest req, AuthenticationException e) {
        log.warn("账号或密码错误！{}", e.getMessage());
        //return CommonResult.failed(ResultCode.INTERNAL_SERVER_ERROR);
        return CommonResult.failed("账号或密码错误");
    }
    /**
     * shiro鉴权
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public CommonResult authorizationExceptionHandler(HttpServletRequest req, AuthorizationException e) {
        log.warn("没有权限 {}", e.getMessage());
        //return CommonResult.failed(ResultCode.INTERNAL_SERVER_ERROR);
        return CommonResult.failed("没有权限");
    }
    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public CommonResult nullPointExceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是：{}", e.getMessage());
        return CommonResult.failed(ResultCode.BODY_NOT_MATCH);
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult otherExceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是：{}", e.getMessage());
        return CommonResult.failed(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
