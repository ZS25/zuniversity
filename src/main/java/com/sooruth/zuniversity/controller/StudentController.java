package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.record.StudentRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public sealed interface StudentController extends ModelController<StudentRecord> permits StudentControllerImpl {
    @GetMapping("/searchOlderThan")
    @ResponseStatus(HttpStatus.OK)
    Page<StudentRecord> readByAgeOlderThan(@RequestParam("page") int page, @RequestParam("size") int size,
                                           @Valid
                                           @Min(value = 18, message = "Age should not be less than 18")
                                           @Max(value = 150, message = "Age should not be greater than 150")
                                           @RequestParam Integer age);

    @GetMapping("/searchByEmail")
    @ResponseStatus(HttpStatus.OK)
    StudentRecord readByEmail(@Valid @Email(message = "Email should be valid")  @RequestParam String email);
}
