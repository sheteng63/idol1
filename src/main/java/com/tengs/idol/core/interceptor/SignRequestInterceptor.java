package com.tengs.idol.core.interceptor;


import com.tengs.idol.core.constant.BzConstant;
import com.tengs.idol.core.util.RedisUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.concurrent.TimeUnit;

public class SignRequestInterceptor implements WebRequestInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SignRequestInterceptor.class);

    @Autowired
    private RedisUtil redis;

    @Override
    public void preHandle(WebRequest request) throws Exception {

        //获取token
        String token = request.getParameter(BzConstant.TOKEN);
        String userId = redis.getValue(token);
        if (StringUtils.isBlank(userId)) {

        }

    }


    @Override
    public void postHandle(WebRequest request, ModelMap modelMap) throws Exception {
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }
}
