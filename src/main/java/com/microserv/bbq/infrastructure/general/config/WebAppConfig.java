package com.microserv.bbq.infrastructure.general.config;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.microserv.bbq.infrastructure.general.commonshare.security.LoginUserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * web app 配置类
 *
 * @author jockeys
 * @since 2020/3/10
 */
@Slf4j
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    //自定义fast-json消息转换器，springboot2会自动添加
    @Bean
    public FastJsonHttpMessageConverter getFastJsonHttpMessageConverter() {
        //创建消息转换器
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        //配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullListAsEmpty, //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullBooleanAsFalse,//Boolean字段如果为null,输出为falseJ,而非null
                SerializerFeature.DisableCircularReferenceDetect, //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.WriteMapNullValue//是否输出值为null的字段,默认为false


        );
        fastJsonConfig.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);

        //媒体类型
        List<MediaType> mediaTypeList = Arrays.asList(
                MediaType.APPLICATION_JSON,
                new MediaType("application", "*+json")
        );
        //设置到消息转换器

        fastJsonConverter.setSupportedMediaTypes(mediaTypeList);
        log.info("--设置FastJson消息处理器支持的媒体类型:{}", mediaTypeList);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        log.info("--设置FastJson消息处理器全局配置信息:完成");
        return fastJsonConverter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("--设置用户鉴权拦截器LoginUserInterceptor");
        registry.addInterceptor(new LoginUserInterceptor());
        log.info("--设置用户鉴权拦截器LoginUserInterceptor:完成");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 如果SpringBoot自动配置不会自动把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面
        // 那么可以暂时加上这个映射
        log.info("--配置swagger静态资源，如 swagger-ui.html等");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        log.info("--配置swagger静态资源:完成");
    }
}