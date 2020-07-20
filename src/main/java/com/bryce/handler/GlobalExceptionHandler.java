package com.bryce.handler;

import com.bryce.common.BizException;
import com.bryce.common.CommonResult;
import com.bryce.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public CommonResult bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return CommonResult.failed(ResultCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public CommonResult nullPointExceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是：{}", e.getMessage());
        return CommonResult.failed(ResultCode.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult otherExceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是：{}", e.getMessage());
        return CommonResult.failed(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
