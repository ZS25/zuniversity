package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.mapper.UserMapper;
import com.sooruth.zuniversity.record.UserRecord;
import com.sooruth.zuniversity.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
public final class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserControllerImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserRecord> readAll(int page, int size) {
        return userService.readAll(page, size)
                .map(userMapper::userToUserRecord);
    }

    @Override
    public UserRecord readById(Long id) {
        return userMapper.userToUserRecord(userService.read(id));
    }

    @Override
    public HttpEntity<EntityModel> create(UserRecord model) {
        Long savedUserId = userService.create(userMapper.userRecordToUser(model));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUserId)
                .toUri();

        Link selfLink = linkTo(methodOn(UserControllerImpl.class).readById(savedUserId)).withSelfRel();
        EntityModel<UserRecord> resource = EntityModel.of(model, selfLink);
        return ResponseEntity.created(location).body(resource);
    }

    @Override
    public void update(UserRecord model) {
        userService.update(userMapper.userRecordToUser(model));
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);

    }

    @Override
    public UserRecord readByEmail(String email) {
        return userMapper.userToUserRecord(userService.readByEmail(email));
    }
}
