package com.example.restfulwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServiceApplication.class, args); //여기서 내장 톰캣을 같이 구동한다.
    }

    /**
     * 지역 코드 및 언어에 관한 정보 설정 */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver(); //세션을 통해서 로케일 값을 얻어온다
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }

}
