package com.gws.newframe.configuration;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gws.newframe.utils.json.JsonParameterBinder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(new JsonParameterBinder());
    }

    //方案一：不重写这个，就不会报错了
    /*@Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        *//**List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);**//*
        converters.add(fastConverter);
    }*/

    //方案二：加上下面的配置
    /*@Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        *//**List<MediaType> supportedMediaTypes = new ArrayList<>();
     supportedMediaTypes.add(MediaType.APPLICATION_JSON);
     supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
     supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
     supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
     supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
     supportedMediaTypes.add(MediaType.APPLICATION_PDF);
     supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
     supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
     supportedMediaTypes.add(MediaType.APPLICATION_XML);
     supportedMediaTypes.add(MediaType.IMAGE_GIF);
     supportedMediaTypes.add(MediaType.IMAGE_JPEG);
     supportedMediaTypes.add(MediaType.IMAGE_PNG);
     supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
     supportedMediaTypes.add(MediaType.TEXT_HTML);
     supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
     supportedMediaTypes.add(MediaType.TEXT_PLAIN);
     supportedMediaTypes.add(MediaType.TEXT_XML);
     fastConverter.setSupportedMediaTypes(supportedMediaTypes);**//*
        converters.add(fastConverter);
    }*/
}