package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.mapper.StudentMapper;
import com.sooruth.zuniversity.service.StudentService;
import com.sooruth.zuniversity.service.record.StudentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController implements ModelController<StudentRecord> {

    private final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;
    private StudentMapper studentMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentRecord> getAll() {
        List<Student> studentList = studentService.readAll();
        return studentMapper.listStudentsToListStudentRecords(studentList);

    }

    @Override
    public StudentRecord getById(Long id) {
        return studentMapper.studentToStudentRecord(studentService.read(id));
    }

    @Override
    public ResponseEntity<String> save(StudentRecord model) {
        Long savedStudentId = studentService.create(studentMapper.studentRecordToStudent(model));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedStudentId).toUri()).build();
    }

    @Override
    public StudentRecord modify(StudentRecord model) {
        Student student = studentService.update(studentMapper.studentRecordToStudent(model));
        return studentMapper.studentToStudentRecord(student);
    }

    @Override
    public void delete(Long id) {
        studentService.delete(id);
    }
}
