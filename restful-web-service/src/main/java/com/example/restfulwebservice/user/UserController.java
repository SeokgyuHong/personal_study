package com.example.restfulwebservice.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;

    }

    @GetMapping("/users") //users엔드포인트로 매핑된다. GET방식으로 접근할경우 모든 유저를 반환함
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    //GET /users/1 or /uesrs/10 ->String
    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id) { //int 형으로 사용할경우 id부분을 자동으로 int형으로 컨버팅 시켜준다.
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id)); //예외를 처리하기위한 코드
        }

//        // HATEOAS
//        EntityModel<User> entityModel = new EntityModel<>(user);
//        //어떤 메서드를 링크에 걸것인지 (전체사용자목록을 가져오는 메서드)
//        WebMvcLinkBuilder linkTO = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //해당 링크정보를 구성
//        //해당 링크를 all-users라는이름으로 매핑하고 해당정보를 entitiyModel에 추가한다
//        entityModel.add(linkTO.withRel("all-usrs"));
//        //entityModel.add(linkTO(methodOn(UserController.class).deleteUser(id);));
//        entityModel.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete-users").with);
//        entityModel.add(linkTo(methodOn(UserController.class).retrieveUser(id)).withSelfRel());
//
//        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joindate","grade"));
//        //(JsonFilter에서 설정한 UserInfo빈에 해당 필터를 적용를 적용하는 필터 생성
//        MappingJacksonValue mapping = new MappingJacksonValue(entityModel); //MappingJacksonValue클래스에 유저 객체 매
//        mapping.setFilters(filters); //매잉한 객체에 필터 적용

        EntityModel<User> entsityModel=new EntityModel<>(user);
        entityModel.add(linkTo(methodOn(UserController.class).deleteUser(user.getId())).withRel("delete-users").withType("POST"));
        entityModel.add(linkTo(methodOn(UserController.class).retrieveAllUsers()).withRel("retrieveAllUsers").withType("GET"));
        entityModel.add(linkTo(methodOn(UserController.class).retrieveUser(user.getId())).withSelfRel().withType("GET"));
        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);
        mapping.setFilters(new SimpleFilterProvider().addFilter("UserInfo",SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joindate","grade")));
        return mapping;



    }



    //적절한 상태코드를 반환하는게 좋은 api의 조건이된다.
    //모든 요청을 예외 핸들링을 통해 처리하고 POST만을 사용하지말라
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { //requestBody로 들어오는 내용을 자동으로 user로 매핑해준다.
        User savedUser = service.save(user);

        URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); //현재 요청된 리퀘스트 값을 사용하겠다.
        /**
         * ServletUriComponentsBuilder (요청에따른 작업을 처리하고 결과값을 토대로 url를 생성할때사용
         * fronCurrentRequest() 현재요청 uri
         * path("/{id}") <- buildAndExpand()함수 호출에대한 결과값이 id에 반영이된다. 일반 경로만 추가할경우 path(/~~~)착성하면 추가됨
         * to.Uri()를 통해 uri객체로 반환해준다.
         */
        /** ResponseEntity클래스는 HttpEntity를 상속(요청 또는 응답에 대한 헤드와 바디를 포함한다)
         *
         */

//        return ResponseEntity.created(location).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(savedUser,headers, HttpStatus.CREATED);
        //body, header, 메시지 순으로 리턴이된다 이렇게사용하면
    }

    @PutMapping("/users")
    public ResponseEntity<User> modificationUser(@RequestBody User user)
    {
        User modification = service.modification(user);
        if(modification==null)
        {
            throw new UserNotFoundException(String.format("ID[%s] not found",user.getId()));
        }
        return new ResponseEntity<>(modification,HttpStatus.CREATED);


    }



    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        }
        return user;


    }


}
