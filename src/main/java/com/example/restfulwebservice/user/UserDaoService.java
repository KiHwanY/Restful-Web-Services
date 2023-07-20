package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static { // data 3개
        users.add(new User(1,"Kenneth",new Date(),"pass1","701010-1111111"));
        users.add(new User(2,"Alice",new Date(),"pass2","701010-2222222"));
        users.add(new User(3,"Elena",new Date(),"pass3","701010-3333333"));
    }
    // service 클래스 생성
    public List<User> findAll(){
        return users;
    }
    public User save(User user){
        if (user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }


    public User findOne(int id){
        for (User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    //Iterator -> 열과형 데이터의 배열, 리스트 값을 순차적으로 접근하는 메서드
    public User deleteMyId(int id){
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()){//순차적으로
            User user = iterator.next();

            if (user.getId() == id){ // 동일한 id가 있다면 remove(삭제)
                iterator.remove();
                return user;
            }
        }
        return null;
    }


    public User putMyid(int id, User user) {
        users.stream()
                .forEach(userid -> {
                    if (userid.getId() == id){
                        userid.setName(user.getName());
                        userid.setJoinDate(user.getJoinDate());
                    }
                });
        return user;
    }
}
