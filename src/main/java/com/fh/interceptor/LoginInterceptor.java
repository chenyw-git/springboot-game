package com.fh.interceptor;

import com.fh.common.ServerEnum;
import com.fh.common.ServerResponse;
import com.fh.config.AjaxException;
import com.fh.config.Ignore;
import com.fh.model.Game;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod= (HandlerMethod) handler;
        Ignore annotation = handlerMethod.getMethod().getAnnotation(Ignore.class);
        if (annotation == null){
            return true;
        }


        return true;
    }
}
