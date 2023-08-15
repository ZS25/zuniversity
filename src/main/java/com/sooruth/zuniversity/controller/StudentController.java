package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.service.record.StudentRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface StudentController extends ModelController<StudentRecord> {
    @GetMapping("/searchOlderThan/{age}")
    @ResponseStatus(HttpStatus.OK)
    List<StudentRecord> retrieveAllStudentsOlderThan(@PathVariable Integer age);

    @GetMapping("/searchByEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    StudentRecord retrieveStudentByEmail(@PathVariable String email);
}
