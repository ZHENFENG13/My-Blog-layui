package com.site.blog.config;

import com.site.blog.constants.HttpStatusConstants;
import com.site.blog.dto.Result;
import com.site.blog.util.ResultGenerator;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @program: FruitSales
 * @description: 全局异常统一处理类
 * @author: 南街
 * @create: 2019-01-17 10:24
 **/

@ControllerAdvice
public class ApplicationControllerExceptionHandler {

    /**
     * 方法参数效验
     * @param e
     * @return com.zhulin.ascentweb.dto.Result
     * @date 2019/9/8 9:58
     */
    @ExceptionHandler(value = BindException.class)
    public Result BindException(BindException e) {
        return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }


    /**
     * 方法参数效验
     * @param e
     * @return com.zhulin.ascentweb.dto.Result
     * @date 2019/9/8 9:58
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handlerError(HttpServletRequest req, Exception e) {
        return ResultGenerator.genFailResult("出现异常错误,请及时查看后台日志！");
    }


}
