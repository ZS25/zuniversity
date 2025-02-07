package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.mapper.StudentMapper;
import com.sooruth.zuniversity.service.StudentService;
import com.sooruth.zuniversity.record.StudentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
@RestController
@RequestMapping("/students")
public class StudentControllerImpl implements StudentController {

    private final Logger LOG = LoggerFactory.getLogger(StudentControllerImpl.class);

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentControllerImpl(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @Override
    public Page<StudentRecord> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studentService.readAll(page, size)
                .map(studentMapper::studentToStudentRecord);
    }

    @Override
    public StudentRecord getById(Long id) {
        return studentMapper.studentToStudentRecord(studentService.read(id));
    }

    @Override
    public HttpEntity<String> save(StudentRecord model) {
        Long savedStudentId = studentService.create(studentMapper.studentRecordToStudent(model));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedStudentId).toUri()).build();
    }

    @Override
    public StudentRecord modify(StudentRecord model) { //TODO: should not return data
        Student student = studentService.update(studentMapper.studentRecordToStudent(model));
        return studentMapper.studentToStudentRecord(student);
    }

    @Override
    public void delete(Long id) {
        studentService.delete(id);
    }

    @Override
    public List<StudentRecord> retrieveAllStudentsOlderThan(Integer age) {//TODO: pagination like getAll
        return studentMapper.listStudentsToListStudentRecords(studentService.findAllStudentsOlderThan(age));
    }

    @Override
    public StudentRecord retrieveStudentByEmail(String email) {
        return studentMapper.studentToStudentRecord(studentService.findStudentByEmail(email));
    }
}
