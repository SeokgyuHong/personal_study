package com.example.tutorial.service;


import com.example.tutorial.dto.UserDto;
import com.example.tutorial.entity.Authority;
import com.example.tutorial.entity.User;
import com.example.tutorial.entity.test_user;
import com.example.tutorial.repository.UserRepository;
import com.example.tutorial.repository.testUserRepository;
import com.example.tutorial.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final testUserRepository testUserRepository;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,testUserRepository testUserRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.testUserRepository = testUserRepository;
    }

    /**
     * 회원가입 로직 수행하는 메서드
     * @param userDto
     * @return
     */
    @Transactional
    public test_user testSignUp(UserDto userDto) {
        if(testUserRepository.findByEmail(userDto.getEmail()).orElse(null)!=null){
            throw new RuntimeException("이미가입된 유저다");
        }
        test_user user = test_user.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .auth("ROLE_USER")
                .build();
        return testUserRepository.save(user);

    }
//    @Transactional
//    public User signup(UserDto userDto) {
//        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) { //값을 가져오거나 아니면 Null인데 null이아니면 이미있다는의미
//            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
//        }
//
//        /**
//         * 유저 정보가 없으면 권한정보를 만든다
//         * 권한정보의 유저 정보를 만들어서
//         * 유저정보와 권한정보를 저장한다.
//         */
//        //빌더 패턴의 장점
//        Authority authority = Authority.builder()
//                .authorityName("ROLE_USER")
//                .build();
//
//        User user = User.builder()
//                .username(userDto.getUsername())
//                .password(passwordEncoder.encode(userDto.getPassword()))
//                .nickname(userDto.getNickname())
//                .authorities(Collections.singleton(authority))
//                .activated(true)
//                .build();
//
//        return userRepository.save(user);
//    }

    /**
     * 유저네임에 해당된 권한정보를 반환
     * @param username
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<test_user> getUserWithAuthorities(String Email) {
        return testUserRepository.findByEmail(Email);
//        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    /**
     * securityContext에 저장된 username의 정보만 가져온다
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}