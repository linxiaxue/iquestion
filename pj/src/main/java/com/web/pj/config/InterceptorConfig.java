package com.web.pj.config;

import com.web.pj.interceptor.ParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ParamInterceptor paramInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        //此处配置拦截路径
       //registry.addInterceptor(paramInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login/**","/swagger-ui.html/**","/swagger-resources/**","/webjars/**","/v2/**");
    }
    @Override

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
