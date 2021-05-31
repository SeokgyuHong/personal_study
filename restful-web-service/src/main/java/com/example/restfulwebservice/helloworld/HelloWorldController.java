package com.example.restfulwebservice.helloworld;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

//RestController사용 RestAPI일경우
@RestController
public class HelloWorldController {
    @Autowired //자동으로 생성자 주입해준다.
    private MessageSource messageSource;
    //GET
    // /hello-world (endpoint)
    //기존방식에서는 @RequestMapping(method=RequestMethod.GET,path="/hello-world")썼는데 최근에는 잘 사용하지 않음
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

//    //실제 스프링에서 관리되는 빈은 아님 객체를 그냥 빈으로 표현
//    @GetMapping(path = "/hello-world/bean")
//    public HelloWorldBean helloWorldBean() {
//
//        return new HelloWorldBean("Hello-world");//자바빈을 반환하게되면 JSON형태로 변환해서 반환해준다 자동으로 JSON 포맷으로 반환해준다
//    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) //name에 들어있는 부분이 pathVariable이 된다. 어노테이션으로 지정해야함
    {
        return new HelloWorldBean(String.format("Hello World,%s",name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required = false) Locale locale) {

        return messageSource.getMessage("greeting.message", null, locale);
    }
}

