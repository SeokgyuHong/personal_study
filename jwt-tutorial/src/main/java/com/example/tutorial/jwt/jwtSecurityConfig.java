package com.example.tutorial.jwt;

import antlr.Token;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class jwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;
    //토큰프로바이더를 주입받는다.
    public jwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
    //해당 필터를 addFilterBefore한다
    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider); //jwt필터를 시큐리터 로직의 필터로 등록
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class); //usernamePasswordAuthenticationFilter클래스가 해당정보를 통해 인증 담당 인터페이스에게 인증용 ㄱ객체를 생성해 위임한다
        /**해당코드는 인증을 처리하는 기본필터인 usernamePasswordAuthenticationFilter대신 별도의 jwt 인증 로직을 가진 customfilter를 등록해서 사용한다.
         * 지정된 필터앞에 커스텀필터를 생성해서 지정된 필터보다 먼저 실행하게 해주는 addfilterbefore함수
         *
         */

    }
}
