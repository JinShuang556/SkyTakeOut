package com.qrs.handler;

import com.qrs.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
public class GlobalExceptionHandler {
    //用户名相同时抛出
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error("异常信息：{}",ex.getMessage());
        String msg = ex.getMessage();
        if (msg.contains("Duplicate entry")){
            String[] split = msg.split(" ");
            String username = split[2];
            return Result.error("用户名已存在");
        }else {
            return Result.error("未知错误");
        }
    }
}
