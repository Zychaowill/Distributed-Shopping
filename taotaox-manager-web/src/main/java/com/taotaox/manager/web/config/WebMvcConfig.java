package com.taotaox.manager.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by yachao on 18/1/21.
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
        super.configureDefaultServletHandling(configurer);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(jstlViewResolver());
        super.configureViewResolvers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/content").setViewName("content");
        registry.addViewController("/content-add").setViewName("content-add");
        registry.addViewController("/content-category").setViewName("content-category");
        registry.addViewController("/content-edit").setViewName("content-edit");
        registry.addViewController("/file-upload").setViewName("file-upload");
        registry.addViewController("/item-add").setViewName("item-add");
        registry.addViewController("/item-edit").setViewName("item-edit");
        registry.addViewController("/item-list").setViewName("item-list");
        registry.addViewController("/item-param-add").setViewName("item-param-add");
        registry.addViewController("/item-param-list").setViewName("item-param-list");
        super.addViewControllers(registry);
    }

    @Bean
    public ViewResolver jstlViewResolver() {
        InternalResourceViewResolver jstlViewResolver = new InternalResourceViewResolver();
        jstlViewResolver.setPrefix("/WEB-INF/jsp/");
        jstlViewResolver.setSuffix(".jsp");
        jstlViewResolver.setViewClass(JstlView.class);
        return jstlViewResolver;
    }
}
