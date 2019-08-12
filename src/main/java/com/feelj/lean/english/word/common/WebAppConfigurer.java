package com.feelj.lean.english.word.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @Auther: feelj
 * @Date: 2019/8/5 23:24
 * @Description:  路径映射
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir") + File.separator;
        registry.addResourceHandler("/res/**").addResourceLocations("file:"+path);
    }
}
