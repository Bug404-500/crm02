package com.xxxx.crm.exception;

import com.xxxx.crm.base.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultInfo exec(Exception e){
        System.out.println("进入到全局异常处理器");
        ResultInfo info = new ResultInfo();
        if(e instanceof ParamsException){
            info.setCode(200001);
            info.setMsg("参数异常");
            info.setResult(((ParamsException) e).getMsg());
        }else if (e instanceof MyException){
            info.setCode(200002);
            info.setMsg("我的异常");
            info.setResult(((MyException) e).getMsg());
        }else if(e instanceof NullPointerException){
            info.setCode(200003);
            info.setMsg("空指针异常");
        }else if(e instanceof NoCookieException){
            info.setCode(200004);
            info.setMsg("没有cookie");
        }else {
            info.setCode(50000);
            info.setMsg("未知异常");
            info.setResult(e.getMessage());
        }

        return info;
    }
}
