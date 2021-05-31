package com.example.restfulwebservice.helloworld;

//lombok 게터, 세터, 이퀄스등을 자동으로 생성해줌

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //게터, 세터, 이퀄스등 자동으로 생성해주는 어노테이
@AllArgsConstructor //생성자 자동으로 만들어줌 이미 해당 어노테이션에 생성자가 자동으로 등록되기때문에 중복 오류가 발생함
@NoArgsConstructor //디폴트 생성자 만들어줌
public class HelloWorldBean {
    private String message;


}
