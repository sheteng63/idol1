package com.tengs.idol.core.interceptor;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tengs.idol.core.exception.BzException;
import com.tengs.idol.core.util.JwtUtil;
import com.tengs.idol.entity.User;
import com.tengs.idol.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SafeCheckInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SafeCheckInterceptor.class);

    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer:")) {
            throw new BzException("000001", "用户未登录");
        }

        String token = authHeader.substring(7);

        Claims claims = JwtUtil.checkToken(token);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", claims.getSubject());
        User user = userMapper.selectOne(wrapper);
        if (user == null){
            throw new BzException("000002", "非法用户");
        }
        request.setAttribute("user", user);

        return true;

    }

}
