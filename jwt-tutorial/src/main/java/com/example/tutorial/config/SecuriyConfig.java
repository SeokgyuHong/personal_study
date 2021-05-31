package com.example.tutorial.config;

import com.example.tutorial.jwt.JwtAccessDeniedHandler;
import com.example.tutorial.jwt.JwtAuthenticationEntryPoint;
import com.example.tutorial.jwt.TokenProvider;
import com.example.tutorial.jwt.jwtSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 스프링 시큐리티를 사용하기 위해서 filter chain을 사용한다는것을 명시한다 websecurityConfigurerAdapter를 상속받은 클래스에 @enableWebsecurity어노테이션을 달면된다.
 */
@EnableWebSecurity //기본적인 웹 보안을 활성화 하겠다라는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true) //나중에 preauthorize를 메소드 단위로 사용하기위해 사용
public class SecuriyConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    //private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecuriyConfig(
            TokenProvider tokenProvider,
            //CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        //this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }



    //패스워드 인코더 암호화에 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/h2-console/**","/favicon.ico");
                    //h2-console하위의 모든 요청과 파비콘은 무시하는걸로 설정하고 시작
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //토큰을 사용할것이기때문에 csrf를 disable해주
                .csrf().disable()

                //exception을 핸들링해줄때  401에러와 403에러를 핸들링할정보를 추가해준다.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //h2콘솔에대한 정보추가
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                //세션을 사용하지않는다 토큰기반 인증이기 때문에
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests() //httpservletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다는 의미
                .antMatchers("/api/hello").permitAll() //antMatchers(path).permitAll() 해당 패스로 오는 요청은 인증없이 접근을 허용하겠다는 의미
                .antMatchers("/api/login").permitAll()//토큰을받기위한 요청 api
                .antMatchers("/api/signup").permitAll() //로그인 api
                .anyRequest().authenticated() //나머지 요청들에 대해서는 인증을 받아야한다. 권한종류상관없이 권한만 있으면 된다.
                .and()
                .apply(new jwtSecurityConfig(tokenProvider));
    }
}
