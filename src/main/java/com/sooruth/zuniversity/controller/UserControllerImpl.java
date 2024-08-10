package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.entity.User;
import com.sooruth.zuniversity.mapper.UserMapper;
import com.sooruth.zuniversity.record.UserRecord;
import com.sooruth.zuniversity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserControllerImpl.class);

    private final UserService userService;
    private final UserMapper userMapper;

    public UserControllerImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserRecord> getAll(int page, int size) {
        return userService.readAll(page, size)
                .map(userMapper::userToUserRecord);
    }

    @Override
    public UserRecord getById(Long id) {
        return userMapper.userToUserRecord(userService.read(id));
    }

    @Override
    public ResponseEntity<String> save(UserRecord model) {
        Long savedUserId = userService.create(userMapper.userRecordToUser(model));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUserId).toUri()).build();
    }

    @Override
    public UserRecord modify(UserRecord model) { //TODO: should not return data
        User user = userService.update(userMapper.userRecordToUser(model));
        return userMapper.userToUserRecord(user);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);

    }

    @Override
    public UserRecord retrieveUserByEmail(String email) {
        return userMapper.userToUserRecord(userService.findUserByEmail(email));
    }
}
