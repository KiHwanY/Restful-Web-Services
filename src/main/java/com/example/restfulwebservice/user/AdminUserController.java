package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;


    public UserController(UserDaoService service){
        this.service = service;
    }


    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll(); // 전체 사용자 목록
    }

    // GET/users/1 or / users/10 -> String
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){ // 자동으로 int로 처리됨
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
        return user;
    }

    @PostMapping("/users") // 사용자 추가
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteMyId(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
    }
    @PutMapping("/users/{id}")
    public User putUser(@PathVariable int id ,@RequestBody User user){
        service.putMyid(id,user);
        return user;
    }
}
