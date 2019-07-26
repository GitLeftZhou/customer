package com.zhou.customer.component;

import com.zhou.customer.exception.BussException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类用来自定义错误返回结构
 * 默认错误返回结构为
 * timestamp,status,error,exception,message,errors
 */
//@ControllerAdvice  使用时打开这个注释
public class MyExceptionHandler {
    /**
     * @param e
     * @return json
     */
    @ResponseBody
    @ExceptionHandler(BussException.class)
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> map = getException(e);
        return map;
    }

    /**
     * @param e
     * @return txt/html
     */
    @ExceptionHandler(BussException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = getException(e);
        //设置错误码，否则会响应200
        request.setAttribute("javax.servlet.error.status_code", 500);
        return "forward:/error";
    }

    /**
     * 这个方法设置公共响应部分
     *
     * @param e
     * @return
     */
    private Map<String, Object> getException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.bussException");
        map.put("message", e.getMessage());
        map.put("track", e.getStackTrace());
        return map;
    }
}
