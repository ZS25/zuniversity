package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.record.StudentRecord;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public sealed interface StudentController extends ModelController<StudentRecord> permits StudentControllerImpl {
    @GetMapping("/searchOlderThan")
    @ResponseStatus(HttpStatus.OK)
    Page<StudentRecord> readByAgeOlderThan(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam Integer age);

    @GetMapping("/searchByEmail")
    @ResponseStatus(HttpStatus.OK)
    StudentRecord readByEmail(@RequestParam String email);

}
