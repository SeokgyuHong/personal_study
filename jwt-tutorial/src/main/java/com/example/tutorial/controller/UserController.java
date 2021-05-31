package com.example.tutorial.controller;

import com.example.tutorial.dto.UserDto;
import com.example.tutorial.entity.User;
import com.example.tutorial.entity.test_user;
import com.example.tutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<test_user> signup(@Valid @RequestBody UserDto userDto) {
//        return ResponseEntity.ok(userService.signup(userDto));
        return ResponseEntity.ok(userService.testSignUp(userDto));

    }


    /**
     *user role 과 admin role 두개 다 권한 허용
     */
//    @GetMapping("/user")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<User> getMyUserInfo() {
//        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
//        //return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
//    }

    /**
     * admin role에만 권한허용
     * @param username
     * @return
     */
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER,ADMIN')")
    public ResponseEntity<test_user> getUserInfo() {
        String email = "hsg8974@naver.com";
        System.out.println(email);
        return ResponseEntity.ok(userService.getUserWithAuthorities(email).get());
    }
}
