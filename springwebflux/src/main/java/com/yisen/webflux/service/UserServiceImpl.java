package com.yisen.webflux.service;

import com.yisen.webflux.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl {

    Map<Long, User> userMap;
    List<User> users;

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        userMap = new HashMap<>();

        User user1 = new User(1L, "张三");
        User user2 = new User(2L, "李四");
        User user3 = new User(3L, "王五");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        userMap.put(1L, user1);
        userMap.put(2L, user2);
        userMap.put(3L, user3);
    }

    public List<User> getAll() {
        return users;
    }

    public User findById(Long id) {
        return userMap.get(id);
    }
}
