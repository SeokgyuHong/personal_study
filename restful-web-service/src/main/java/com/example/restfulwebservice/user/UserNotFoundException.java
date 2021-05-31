package com.example.restfulwebservice.user;
//HTTP status code를 활용해 처리
//2XX -> OK
//4XX -> Client측 에러
//5xx -> Server측 에러

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND) //해당 예외를 전달할때 발생하는 상태코드는  not_found이다
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message); //부모클래스에 메시지 전달

    }
}
