package com.wyf.myblog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wyf
 * @create 2020/5/29
 */
/*WebMvcConfigurerAdapter已过时*/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    /*指定要拦截的地址*/
    public void addInterceptors(InterceptorRegistry registry) {
        //LoginInterceptor要拦截的对象地址
        registry.addInterceptor(new LoginInterceptor())
                /*除了login和admin下面的地址其他的地址拦截*/
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin");
    }
}

