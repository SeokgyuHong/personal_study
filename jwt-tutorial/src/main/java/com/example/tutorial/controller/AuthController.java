package com.example.tutorial.controller;


import com.example.tutorial.dto.LoginDto;
import com.example.tutorial.dto.TokenDto;
import com.example.tutorial.jwt.JwtFilter;
import com.example.tutorial.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }


    /**
     * 로그인 시도할때마다 서버측은 해당정보를 검증해서 서명된 토큰을 발급한다. 해당 토큰을 가지고 추후 요정때마다 검증을 통해 맞으면 응답해준다.
     */
//    @PostMapping("/authenticate")
//    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
//
//        /**
//         * 파라미터를 통해 authenticationToken객체를 생성한다. LoginDto는 id password정보를 담아옴
//         */
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
//        /**
//         * authenticationToken을 이용해서 authenticate메서드가 실행될때 customeUserdetailsService에서 우리가 생성한 loadUserbyusername 메서드가 실행된다.
//         * 결과값을 토대로 authentication객체를 생성한다 생성한걸 securityContextholder에 저장하고 인증정보를 기반으로 토큰 프로바이더에서 생성한 createtoken메서드를 활용해 토큰을만든다
//            토큰을 헤더에도 넣어주고 바디에도 넣어서 리턴해줌
//         */
//        //아래 로직을 통해 디비에 있는 사용자 비밀번호를 체크한다 일치하지않으면 예외발생 이후 로직 실행 x
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = tokenProvider.createToken(authentication);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,"Bearer "+ jwt);
//        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
//    }
    //로그인시에 토큰 생성해서 리턴해준다.
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {

        /**
         *
         * 사용자의 이메일과 패스워드를 활용해서 usernamePasswordAuthenticationTOken인증 객체을 만든다. 해당 객체에 저장된 정보가 동일한지를 판단해야하는데
         * 해당 로직을 수행하는게 아래의 getObject().authenticate다. 해당 authenticate메서드를 실행하면
         * customeUserdetailService에있는(UserDetailService인터페이스를 구현해야) loaduserbyusername메서드(당연히 오버라이딩) 실행된다.
         * 해당 메서드에서 실제 db에서 회원정보를 가져오는 로직을 구현해야한다. 그리고 해당 메서드는 userDetails라는 인터페이스를 리턴해야하는데
         * UserDetails를 구현하는 스프링제공 User객체를 사용했다. UserDetails는 db에서 찾은 회원정보를 가지고있기때문에 패스워드는 직접 인코딩되어서 저장되어있다.
         *
         *
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        /**
         * 결과값을 토대로 authentication객체를 생성한다 생성한걸 securityContextholder에 저장하고 인증정보를 기반으로 토큰 프로바이더에서 생성한 createtoken메서드를 활용해 토큰을만든다
         토큰을 헤더에도 넣어주고 바디에도 넣어서 리턴해줌
         */
        //아래 로직을 통해 디비에 있는 사용자 비밀번호를 체크한다 일치하지않으면 예외발생 이후 로직 실행 x
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,"Bearer "+ jwt);
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
