package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> retrieveUser(@PathVariable int id){ // 자동으로 int로 처리됨
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
        //EntityModel의 생성자는 protected로 되어 있어 바로 사용할 수 없다.
        //EntityModel을 새로 생성하는게 아닌, of 메서드를 사용해서 연결해줘야한다.
        //HATEOAS
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));


        return model;
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
