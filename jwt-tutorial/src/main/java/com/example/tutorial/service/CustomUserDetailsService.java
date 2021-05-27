package com.example.tutorial.service;

import com.example.tutorial.entity.User;
import com.example.tutorial.entity.test_user;
import com.example.tutorial.repository.UserRepository;
import com.example.tutorial.repository.testUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final testUserRepository testUserRepository;

    //유저 리포지토리 주입
    public CustomUserDetailsService(UserRepository userRepository,testUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;

        this.userRepository = userRepository;
    }

    //해당 기능을 구현해야 디비에있는 유저네임을 실제로 가져올수있다. 해당 기능을 통해 로그인시 AUtenticationBuilder에서 검증 절차를 거친다.
    //로그인시 Db에서 유저정보와 권한정보를 가져온다
    //해당 정보에서 가져오는 이미 등록된 계정의 권한을 통해 grantedAuthority가 토큰의 권한이 된다.
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(final String username) {
//        return userRepository.findOneWithAuthoritiesByUsername(username).map(user -> createUser(username,user))
//                .orElseThrow(()-> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
//    }

    //해당 유저가 활성화 상태일 경우 유저 객체를 생성해서 가져온다
//    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
//        if(!user.isActivated()){
//            throw new RuntimeException(username + " -> 활성화 되어 있지 않습니다.");
//
//        }
        /**
         * 해당 부분에서 user.getcode나 그런식으로 유저계정의 카운트를 파악해서 각 카운트마다 권한을 다르게 설정해줄수잇지않ㅇ르까 ? 1이면 role_user, 2면 role_admin이런식
         * */

//        List<GrantedAuthority> granedAuthorities = user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
//                .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),granedAuthorities);
//    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        return testUserRepository.findByEmail(email).map(test_user -> createUser(email, test_user)).orElseThrow(() -> new UsernameNotFoundException(email+"-> db에서 찾을수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, test_user user){
        List<GrantedAuthority> list = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuth());
        System.out.println(grantedAuthority);
        list.add(grantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),list);
    }
}
