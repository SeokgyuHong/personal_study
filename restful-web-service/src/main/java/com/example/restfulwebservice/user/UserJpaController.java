package com.example.restfulwebservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/jpa") //모든 메서드가 프리픽스 가지는경우 사용
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    //localhost:8088/jpa/users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();

    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retreiveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id); //옵셔널로 값을 반환한다 데이터가 없을수도 있기때문에

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        EntityModel<User> entityModel = new EntityModel<>(user.get());
        entityModel.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("retreive-all"));

        return entityModel;

    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("users/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//지금 추가한 url인 users/에
                .path("/{id}").path("/{hello}").queryParam("key",savedUser.getSsn())
                .buildAndExpand(savedUser.getId(),savedUser.getName())
                .toUri();

        /**각 path {값}에느,ㄴ Buildandexpand메서드에 넣은 값이 들어간다. 쿼리스트링으로 만들고 싶은경우 queryParam으로 만들면 됨
         *201번 생성 응답이나, 300번대 응답의 경우 어디로 이동할지 알려줄수있게 만든다.
         */

        return ResponseEntity.created(location).build();

    }

    // /jpa/users/90001/posts 호출가능
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostByUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


        return user.get().getPosts();

    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {

        Optional<User> user = userRepository.findById(id);
        //포스트 entity에는 유저 도메인이 있기때문에 똑같이 처리해줘야한다.
        post.setUser(user.get()); //사용자정보를 지정해줘야함 매핑이 되어있기때문에
        Post savedPost = postRepository.save(post); //유저정보도 함께 포함해줘야하기때문에
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//지금 추가한 url인 users/에
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        /**각 path {값}에느,ㄴ Buildandexpand메서드에 넣은 값이 들어간다. 쿼리스트링으로 만들고 싶은경우 queryParam으로 만들면 됨
         *201번 생성 응답이나, 300번대 응답의 경우 어디로 이동할지 알려줄수있게 만든다.
         */

        return ResponseEntity.created(location).build();

    }
}
