package com.bryce.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.bryce.common.BizException;
import com.bryce.common.CommonResult;
import com.bryce.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public CommonResult nullPointExceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是：{}", e.getMessage());
        return CommonResult.failed(ResultCode.BODY_NOT_MATCH);
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult otherExceptionHandler(HttpServletRequest req, Exception e) {

        if (e instanceof NotLoginException) {	// 如果是未登录异常
            log.error("未登录");
            return CommonResult.failed("未登录");
        } else if(e instanceof NotPermissionException) {	// 如果是权限异常
            log.error("无此权限,原因是：{}",e.getMessage());
            return CommonResult.failed("无此权限");
        }
        log.error("未知异常！原因是：{}", e.getMessage());
        return CommonResult.failed(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
