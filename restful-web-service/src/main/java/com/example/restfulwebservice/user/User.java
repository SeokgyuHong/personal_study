package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor //모든필드에 대해 파라미터로 받는 생성자
//@JsonIgnoreProperties(value={"password","ssn"}) //해당 프로퍼티를 사용시 password ssn을 무시하고 보낸다
@NoArgsConstructor
//@JsonFilter("UserInfo") //해당 필터 사용시 mapping과 필터를 활용해서 값을 리터해야한다 . 해당방식을 사용하면 컨트롤러마다 리턴할수있는 객체의 내용이 달라진다.
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체") //swagger documentation에서 활용
@Entity
public class User { //해당 도메인의 validation 값 설정
    @Id
    @GeneratedValue //자동생성값
    private Integer id;

    @Size(min = 2,message = "Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;

    @Past //과거데이터만 가능
    @ApiModelProperty(notes = "사용자 등록일을 입력해 주세요.")
    private Date joindate;

    
    //jakson라이브러리에 포함된 내용 @JsonIgnore 사용시 json 리턴시 해당 필드를 포함하지않고 리턴한다
    //@JsonIgnore
    @ApiModelProperty(notes = "사용자 패스워드 입력해 주세요.")
    private String password;
    //@JsonIgnore를
    @ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요.")
    private String ssn;


    /**mappedByr가 없을경우 jpa에서 양방향인걸 모르고 두 엔티티간의 매핑 테이블을 생성해벌니다
     * many가 되는 쪽이 fk를 가지기 때문에 owner가 된다고 생각하면 되고 mappedBy는 @oneTomany컬럼쪽에 기술하고
     *
     *
     */

    //유저 도메인에서는 Post값을 1대 다로 가져오면 된다.주인은 MappedBy속성을 사용할 수 없으니 주인이 아닌 category엔티티의 booksvlfemdp mappedBy속서을 사용해서 주인이 아님을 알려준다.
    //mappedBy 에 user를 써준 이유는 Post테이블에서 user를 참조할때 작성한 필드명을 적는다. 그래서 아까 User라고 적어서 틀린거엿음.
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int id, String name, Date joindate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joindate = joindate;
        this.password = password;
        this.ssn = ssn;
    }
}
