package com.zhou.customer.config;

import com.zhou.customer.component.LoginHandlerInterceptor;
import com.zhou.customer.component.TemplatesResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("blank");
        registry.addViewController("/login.html").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html", "/", "/login", "/sys/login");
    }

    @Bean
    public TemplatesResolver templateResolver() {
        TemplatesResolver tr = new TemplatesResolver();
        //注意template/前面没有"/"，也不要加"classpath:"，
        //解决通过mvn打包成jar后找不到模版文件问题
        tr.setPrefix("templates/");
        tr.setSuffix(".html");
        tr.setTemplateMode("HTML5");
        tr.setOrder(0);
        tr.setCharacterEncoding("UTF-8");
        return tr;
    }

}
