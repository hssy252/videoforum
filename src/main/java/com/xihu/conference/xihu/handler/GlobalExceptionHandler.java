package com.xihu.conference.xihu.handler;

import com.xihu.conference.xihu.constant.MessageConstant;
import com.xihu.conference.xihu.entity.Result;
import com.xihu.conference.xihu.exception.BaseException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    //@ResponseStatus
    public Result handleException(BaseException e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage(): "操作失败");
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        //Duplicate entry ‘name’ for key ‘goods.name’
        String message = ex.getMessage();
        if (message.contains("Duplicate entry"))
        {
            String[] splits = message.split(" ");
            String username = splits[2];
            String msg = username+ MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }
        else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
