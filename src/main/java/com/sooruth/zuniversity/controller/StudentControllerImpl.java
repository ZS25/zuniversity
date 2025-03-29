package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.mapper.StudentMapper;
import com.sooruth.zuniversity.record.StudentRecord;
import com.sooruth.zuniversity.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/students")
public final class StudentControllerImpl implements StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentControllerImpl(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @Override
    public Page<StudentRecord> readAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studentService.readAll(page, size)
                .map(studentMapper::studentToStudentRecord);
    }

    @Override
    public StudentRecord readById(Long id) {
        return studentMapper.studentToStudentRecord(studentService.read(id));
    }

    @Override
    public HttpEntity<EntityModel> create(StudentRecord model) {
        Long savedStudentId = studentService.create(studentMapper.studentRecordToStudent(model));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedStudentId)
                .toUri();

        Link selfLink = linkTo(methodOn(StudentControllerImpl.class).readById(savedStudentId)).withSelfRel();
        EntityModel<StudentRecord> resource = EntityModel.of(model, selfLink);
        return ResponseEntity.created(location).body(resource);
    }

    @Override
    public void update(StudentRecord model) {
        studentService.update(studentMapper.studentRecordToStudent(model));
    }

    @Override
    public void delete(Long id) {
        studentService.delete(id);
    }

    @Override
    public Page<StudentRecord> readByAgeOlderThan(int page, int size, Integer age) {
        return studentService.readByAgeOlderThan(age, page, size).map(studentMapper::studentToStudentRecord);
    }

    @Override
    public StudentRecord readByEmail(String email) {
        return studentMapper.studentToStudentRecord(studentService.readByEmail(email));
    }
}
