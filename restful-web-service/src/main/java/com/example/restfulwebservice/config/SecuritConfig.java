//package com.example.restfulwebservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Configurtion어노테이션을 활용하면 스프링부트가 설정정보를 메모리에 같이 로딩시킨다.
// * */
////@Configuration
////public class SecuritConfig extends WebSecurityConfigurerAdapter {
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth)
////        throws Exception{
////        auth.inMemoryAuthentication()
////                .withUser("kenneth")
////                .password("{noop}test1234")
////                .roles("USER");
////    }
////}
