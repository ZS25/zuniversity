package com.sooruth.zuniversity.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @Value("${zuni.welcomeMessage: default welcome message}")
    private String welcomeMessage;
    @GetMapping
    public String returnsString(){
        return welcomeMessage;
    }
}
