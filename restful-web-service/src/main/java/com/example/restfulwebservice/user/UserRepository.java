package com.example.restfulwebservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 새로운 메서드가 필요할경우 UserRepository에 추가한다. 기본적으로 UserRepository의 경우 JpaRepository를 상속받기때문에
 * CRUD 사용가능
 * 해당 기능은 spring data jpa
 */

@Repository //빈의 타입을 repository로 등록한다.
public interface UserRepository extends JpaRepository<User,Integer> {
    /***
     * 추가 해야할 메서드가 있을경우 여기에 추가하면된다.
     */

    @Query("select DISTINCT a from User a join fetch a.posts")
    List<User> findAllJoinFetch();


}
