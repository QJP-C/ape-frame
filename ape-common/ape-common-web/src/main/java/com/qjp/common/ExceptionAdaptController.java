package com.qjp.common;


import com.qjp.bean.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdaptController {
    @ExceptionHandler({RuntimeException.class})
    public Result runTimeException(RuntimeException exception){
        exception.printStackTrace();
        return Result.fail();
    }
    @ExceptionHandler({Exception.class})
    public Result exception(RuntimeException exception){
        exception.printStackTrace();
        return Result.fail();
    }
}
