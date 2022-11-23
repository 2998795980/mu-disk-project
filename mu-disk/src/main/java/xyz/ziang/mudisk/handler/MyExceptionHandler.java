package xyz.ziang.mudisk.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.ziang.mudisk.common.result.ApiResult;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult<String> exceptionHandler(Exception e) {
        return ApiResult.failed(e.getMessage());
    }
}
