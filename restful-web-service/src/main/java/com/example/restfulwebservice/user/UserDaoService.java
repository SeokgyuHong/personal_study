package com.example.restfulwebservice.user;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//일단 임시로 메모리에 저장
//보통 비즈니스의 로직을 서비스로 두고 개발한다.

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount =3;
    //클래스가 로딩되고 클래스 변수가 준비된후 자동으로 실행되는 블록
    //주로 클래스 변수를 초기화 시키는 코드를 둔다
    static{
        users.add(new User(1, "Kenneth", new Date(),"test1","701010-1111111"));
        users.add(new User(2, "Alice", new Date(),"test2","801111-2222222"));
        users.add(new User(3, "Elena", new Date(),"test3","901313-1111111"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;

    }
    public User modification(User user){
        for (User dbuser : users) {
            if(dbuser.getId()==user.getId())
            {
                dbuser.setJoindate(user.getJoindate());
                dbuser.setName(user.getName());
                return dbuser;
            }
        }
        return null;
    }

    public User findOne(int id) {
        for (User user : users){
            if(user.getId()==id){
                return user;
            }
        }

        return null;
    }
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId()==id)
            {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
