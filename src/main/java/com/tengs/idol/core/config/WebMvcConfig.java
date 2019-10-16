package com.tengs.idol.core.config;


import com.tengs.idol.core.interceptor.SafeCheckInterceptor;
import com.tengs.idol.core.interceptor.SignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ips
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(safeCheckInterceptor())
                // 拦截路径
                .addPathPatterns("/**")
                .excludePathPatterns("/idol/user/login");
        registry.addWebRequestInterceptor(signRequestInterceptor())
                // 拦截路径
                .addPathPatterns("/**")
                .excludePathPatterns("/idol/user/login");

    }

    @Bean
    public SafeCheckInterceptor safeCheckInterceptor() {
        return new SafeCheckInterceptor();
    }

    @Bean
    public SignRequestInterceptor signRequestInterceptor() {
        return new SignRequestInterceptor();
    }

}
