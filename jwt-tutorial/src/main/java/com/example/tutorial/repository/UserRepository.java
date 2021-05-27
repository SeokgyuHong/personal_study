package com.example.tutorial.repository;


import com.example.tutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.Optional;

//참조할 엔티티 객체가 첫번째로 나오고 다음에는 id의 타입을 의미한다
public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "authorities") //쿼리가 수행될때 lazy조회가 아닌 eager조회로 같이가져온다
    Optional<User> findOneWithAuthoritiesByUsername(String username); //유저네임을 기반으로 유저를 가져오는데 권한정보도 같이 가져온다

}
