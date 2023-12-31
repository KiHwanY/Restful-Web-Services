package com.example.restfulwebservice.user;
// HTTP Status Code
//2XX -> OK
//4XX -> Client error
//5XX -> Server error

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message); // 부모 message로 리턴
    }
}
