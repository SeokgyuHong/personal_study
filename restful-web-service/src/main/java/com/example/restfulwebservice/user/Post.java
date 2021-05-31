package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue //자동생성
    private Integer id;

    private String description;


    //USer : Post  -> 1: (0~N), Main : Sub -> Parent : child
    //POst 입장에선 USer가 1개만 와야하기때무네 many to one
    //fetchType.lazy로 포스트데이터가 로딩될때 필요한 데이터를 가져온다
    //자동으로 USER_ID 컬럼을 생성한다
    @ManyToOne(fetch = FetchType.LAZY) //Post값은 many가 가능하고 User의 경우 1개만 올수있다.
    //lazy로 할경우 join시 n+1쿼리문제 발생가능 user 조회 1번 post 조회 n 번
    @JsonIgnore //JSONIgnore 설정시 해당 데이터는 외부로 보여지지 않음. JSON으로 내보낼때
    private User user;

}
