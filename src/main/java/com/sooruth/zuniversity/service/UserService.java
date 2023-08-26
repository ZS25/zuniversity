package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.User;

public interface UserService extends EntityService<User>{
    User findUserByEmail(String email);
}
