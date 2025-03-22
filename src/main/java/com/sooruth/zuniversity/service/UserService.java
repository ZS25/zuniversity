package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.User;

public sealed interface UserService extends EntityService<User> permits UserServiceImpl{
    User findUserByEmail(String email);
}
