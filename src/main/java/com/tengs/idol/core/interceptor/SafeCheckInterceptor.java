package com.tengs.idol.core.interceptor;


import com.tengs.idol.core.constant.BzConstant;
import com.tengs.idol.core.util.RedisUtil;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SafeCheckInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SafeCheckInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String token = request.getHeader(BzConstant.TOKEN);
        if (StringUtil.isBlank(token)){

        }
        return true;
    }

}
