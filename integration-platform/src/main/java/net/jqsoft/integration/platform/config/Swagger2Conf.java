package net.jqsoft.integration.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: yufeng
 * @date: 2022/2/24
 * @description:
 */
@Configuration
@EnableOpenApi
public class Swagger2Conf {

    @Value("${swagger.enabled}")
    private boolean enableSwagger;

    @Bean
    public Docket getUserDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("集成平台")//api标题
                .description("集成平台接口描述")//api描述
                .version("1.0.0")//版本号
                .build();
        return new Docket(DocumentationType.OAS_30)//文档类型（swagger2）
                .enable(enableSwagger)//
                .apiInfo(apiInfo)//设置包含在json ResourceListing响应中的api元信息
                .select()//启动用于api选择的构建器
                .apis(RequestHandlerSelectors.basePackage("net.jqsoft.integration.platform"))
                //扫描接口的包
                .paths(PathSelectors.any())//路径过滤器（扫描所有路径）
                .build();
    }
}
