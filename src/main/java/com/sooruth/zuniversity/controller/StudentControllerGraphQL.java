package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.record.StudentRecord;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentControllerGraphQL{

    List<StudentRecord> getAll();

    StudentRecord getById(@PathVariable Long id);

    ResponseEntity<String> save(@Valid @RequestBody StudentRecord studentRecord);

    StudentRecord modify(@Valid @RequestBody StudentRecord studentRecord);

    void delete(@PathVariable Long id);

}
