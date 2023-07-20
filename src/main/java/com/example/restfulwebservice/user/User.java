package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

// @JsonIgnore  // 필드에 노출이 안됨
@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password","ssn"}) // @JsonIgnore를 클래스 블록 처리
public class User {

    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;

    private String password;

    private String ssn;


}
