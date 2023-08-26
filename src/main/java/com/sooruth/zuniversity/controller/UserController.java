package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.record.UserRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface UserController extends ModelController<UserRecord> {
    @GetMapping("/searchByEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    UserRecord retrieveUserByEmail(@PathVariable String email);
}
