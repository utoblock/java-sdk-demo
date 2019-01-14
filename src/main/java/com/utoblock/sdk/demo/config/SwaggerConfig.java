package com.utoblock.sdk.demo.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：swagger配置
 * 创建人：yoan
 * 创建日期：2018/10/19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docketSdk() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo")
                .apiInfo(apiInfo())
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                .protocols(Sets.newHashSet("http", "https"))
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.utoblock.sdk.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .enableUrlTemplating(false)
                .useDefaultResponseMessages(false);
    }

    /**
     * 构建api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("超凡部落sdk demo")
                //创建人
                .contact(new Contact("lyx", "www.geetion.com", "yoanliang1992@163.com"))
                //版本号
                .version("1.0")
                //描述
                .description("超凡部落sdk demo文档")
                .build();
    }

}
