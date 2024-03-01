package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.mapper.StudentMapper;
import com.sooruth.zuniversity.record.StudentRecord;
import com.sooruth.zuniversity.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentControllerGraphQLImpl implements StudentControllerGraphQL {

    private final Logger LOG = LoggerFactory.getLogger(StudentControllerGraphQLImpl.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentControllerGraphQLImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @QueryMapping(name = "students")
    public List<StudentRecord> getAll() {
        List<Student> studentList = studentRepository.findAll();
        return studentMapper.listStudentsToListStudentRecords(studentList);
    }

    @Override
    public StudentRecord getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> save(StudentRecord studentRecord) {
        return null;
    }

    @Override
    public StudentRecord modify(StudentRecord studentRecord) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
