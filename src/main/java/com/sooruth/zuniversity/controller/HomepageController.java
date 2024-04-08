package com.sooruth.zuniversity.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomepageController {

    @Value("${zuni.welcomeMessage: default welcome message}")
    private String welcomeMessage;
    @GetMapping
    public String homePage(Principal principal){
        return String.format(welcomeMessage, principal.getName());
    }
}
