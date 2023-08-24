package com.sooruth.zuniversity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myLoan")
public class LoanController {

    @GetMapping
    public String returnsString(){
        return "myLoan page";
    }
}
