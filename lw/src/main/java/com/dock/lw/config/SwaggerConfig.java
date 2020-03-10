package com.dock.lw.config;

import com.dock.lw.common.Constant;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    private static final List<Parameter> parameterList = new ArrayList<>();
    static {
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name("token")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        parameterList.add(tokenBuilder.build());
//        ParameterBuilder orgIdBuilder = new ParameterBuilder();
//        orgIdBuilder.name("orgid")
//                .description("orgid")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false).build();
//        parameterList.add(orgIdBuilder.build());
    }


    @Bean
    public Docket createAppRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("/" + Constant.PLATFORM.APP + "/.*"))
                .build().groupName(Constant.PLATFORM.APP)
                .globalOperationParameters(parameterList);
    }

    @Bean
    public Docket createManageRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("/" + Constant.PLATFORM.MANAGE + "/.*"))

                .build().groupName(Constant.PLATFORM.MANAGE)
                .globalOperationParameters(parameterList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("LW API ")
                .version("1.0")
                .build();
    }
}
