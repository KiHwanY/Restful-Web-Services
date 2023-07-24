package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

// @JsonIgnore  // 필드에 노출이 안됨
@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password","ssn"}) // @JsonIgnore를 클래스 블록 처리
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonFilter("UserInfoV2")
public class UserV2 extends User {
    private String grade; // 등급


}
