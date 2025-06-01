package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.record.UserRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public sealed interface UserController extends ModelController<UserRecord> permits UserControllerImpl {
    @GetMapping("/searchByEmail")
    @ResponseStatus(HttpStatus.OK)
    UserRecord readByEmail(@Valid @Email(message = "Email should be valid") @RequestParam String email);
}
