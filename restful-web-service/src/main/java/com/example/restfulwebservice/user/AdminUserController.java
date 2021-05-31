package com.example.restfulwebservice.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.logging.Filter;

@RestController
@RequestMapping("/admin") //해당 클래스의 메소드가 uri 로 매핑될때 /admin이 필수로 붙고 뒤에 users가 붙어야한다.
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;

    }

    @GetMapping("/users") //users엔드포인트로 매핑된다. GET방식으로 접근할경우 모든 유저를 반환함
    public MappingJacksonValue retrieveAllUsers() {

        //SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","password","ssn");

        List<User> all = service.findAll();

        //FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(all);
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",SimpleBeanPropertyFilter.filterOutAllExcept("id","name","password","ssn"));
        mapping.setFilters(filters);



        return mapping;

    }

    //GET /admin/users/1 ->/admin/v1/users/1
    //@GetMapping("/v1/users/{id}") uri를활용한 버전관리방법
    //@GetMapping(value="/users/{id}",params = "version=1") 파라미터를 활용한 버전관리 방법 (/users/id?version=1
    //@GetMapping(value = "/users/{id}",headers = "X-API-VERSION=1") //헤더를 활용한 방법
    @GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) { //int 형으로 사용할경우 id부분을 자동으로 int형으로 컨버팅 시켜준다.
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id)); //예외를 처리하기위한 코드
        }


        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joindate", "password","ssn"); //해당 정보를 필터에서 제외시킨다 ( 즉 해당정보를 가져오는 필터생성)

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
                //(JsonFilter에서 설정한 UserInfo빈에 해당 필터를 적용를 적용하는 필터 생성
        MappingJacksonValue mapping = new MappingJacksonValue(user); //MappingJacksonValue클래스에 유저 객체 매
        mapping.setFilters(filters); //매잉한 객체에 필터 적용
        return mapping;

    }

    //@GetMapping("/v2/users/{id}")
    //@GetMapping(value = "/users/{id}",params = "version=2") 파라미터를 활용한 버전관리 방법
    //@GetMapping(value = "/users/{id}",headers = "X-API-VERSION=2") //헤더를 활용한 방법
    @GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) { //int 형으로 사용할경우 id부분을 자동으로 int형으로 컨버팅 시켜준다.
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id)); //예외를 처리하기위한 코드
        }


        //User -> User2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user,userV2); //두 빈간의 공통 프로퍼티가 있을경우 카피하는 프로퍼티 사용
        userV2.setGrade("VIP");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joindate","grade"));
        //(JsonFilter에서 설정한 UserInfo빈에 해당 필터를 적용를 적용하는 필터 생성
        MappingJacksonValue mapping = new MappingJacksonValue(userV2); //MappingJacksonValue클래스에 유저 객체 매
        mapping.setFilters(filters); //매잉한 객체에 필터 적용
        return mapping;

    }




}
