package com.fisherman.service;

import com.fisherman.model.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    List<User> selectUserByName(String name);
}
