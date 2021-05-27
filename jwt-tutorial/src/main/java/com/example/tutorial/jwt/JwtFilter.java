package com.example.tutorial.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    /***
     * 토큰헤더 설정
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private TokenProvider tokenProvider; //tokenprovider를 주입받는다

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    /**dofilter메서드를 오버라이드 한다 실제 필터링 로직은 Dofilter메서드 내에 들어가고 jwt 토큰의 인증정보를 securityContext에 저장하는 역할 수행
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest); //리퀘스트에서 토큰을 받아오는 메서드
        String requestURI = httpServletRequest.getRequestURI();

        //리퀘스트 헤더에 토큰이 있고 토큰에 유효성 검증 메서드를 통과했다면
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { //해당토큰이 유효성 검증 메서드를 통과하고
            Authentication authentication = tokenProvider.getAuthentication(jwt); //토큰이 정상적이면 토큰에서 authentication객체를 받아와서
            SecurityContextHolder.getContext().setAuthentication(authentication); //security객체에 set 해준다.
            //securityContextHolder에 토큰정보가 들어갈경우 추후 해당 토큰을 통해 접근을 처리할 수 있다.
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**리퀘스트 헤더에서 토큰 정보를 꺼내오기 위한 reslove token 메서드 추가
     *
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER); //헤더에서 토큰정보를 꺼내
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}