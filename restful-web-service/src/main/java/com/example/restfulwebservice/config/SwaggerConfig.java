package com.example.restfulwebservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//configuration 용도로 사용
/**
 * swagger파일에 의해 만들어진 내용이 json 타입으로 보여진다*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //컨택트 정보
    private static final Contact DEFAULT_CONTACT = new Contact("seokgyu Hong", "http://localhost",
            "hsg8974@email.com");

    //api 정보
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("AWEsome API Title",
            "My User management REST API service","1.0","urn:tos",DEFAULT_CONTACT,"Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json","application/xml"));
    /**
     * Docket : documentation화 해서 반환해주는 클래스를 의미*/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

}
