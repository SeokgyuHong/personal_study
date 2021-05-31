package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor //모든필드에 대해 파라미터로 받는 생성자
//@JsonIgnoreProperties(value={"password","ssn"})
@JsonFilter("UserInfoV2")
@NoArgsConstructor
public class UserV2 extends User{ //해당 도메인의 validation 값 설정
    private String grade;



}
