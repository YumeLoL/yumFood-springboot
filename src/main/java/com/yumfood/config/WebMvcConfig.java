package com.yumfood.config;

import com.yumfood.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("The static resource is mapping ..........");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //use Jackson, convert Java to be json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //add messageConverter into MVC
        converters.add(0,messageConverter);
        super.extendMessageConverters(converters);
    }

}
