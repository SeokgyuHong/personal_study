package com.example.tutorial.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 토큰의 생성, 유효성 검증을 담당한다.
 * initializingBean 인터페이스 구현시 afterPropertiesSEt 메서드를 구현해서 빈 객체 초기화시 필요한 코드를 구현한다
 */
@Component
public class TokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class); //로그를 남기기 위한 로거
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret; //생성자 통해서 초기화 가능
    private final long tokenValidityInMilliseconds;

    private Key key;

    /**
     *  application.yml 파일에 있는 jwt.secret값과 jwt.token-validatiy값을 의미한다. 기본값을 넣어준다고 생각하면됨
     */

    /**
     * application.yml파일에서 입력한 secret값
     * @param secret
     * @param tokenValidityInMilliseconds
     */
    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds){
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    /**
     * InitializingBean을 implements해서 afterPropertiesSet을 오버라이딩 한 이유는 빈 생성후 주입받은 후에 secret값을 base64 디코드해서 키변수에 할당하기위해
     */
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     *authentication객체의 권한정보를 이용해서 토큰을 생성하는 createToken메서드
     */

    public String createToken(Authentication authentication) { //key와 autorities (권한을 통한 클레임 설정)
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds); //현재시간부터 yml파일에 지정해둔 토큰의 expire타임 설정해준다

        //jwt토큰을 생성해서 리턴한다.
        return Jwts.builder()
                .setSubject(authentication.getName()) //토큰용도
                .claim(AUTHORITIES_KEY, authorities) //claim설정
                .signWith(key, SignatureAlgorithm.HS512) //서명을 의미
                .setExpiration(validity) //만료시간
                .compact(); //토큰생성
    }

    /**
     * 토큰 정보를 역으로 받아서 토큰의 정보를 활용해 Authentication 객체 리턴하는 메서드
     */

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts //토큰을 이용해서 클레임 생성
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        //클레임에서 권한정보를 빼온다.
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        //유저객체를 만들
        User principal = new User(claims.getSubject(), "", authorities);
      //유저객체와 토큰 권한정보를 authentication객체 리턴
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**토큰 유효성검사를 위한 메서드
     * 토큰 파싱후 발생하는 익셉션 캐치해서 문제잇을겨우 false
     * */

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
