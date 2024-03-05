package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.mapper.StudentMapper;
import com.sooruth.zuniversity.record.StudentRecord;
import com.sooruth.zuniversity.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
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
    public List<StudentRecord> getAll() {
        List<Student> studentList = studentRepository.findAll();
        return studentMapper.listStudentsToListStudentRecords(studentList);
    }

    @Override
    public Student save(String firstName, String lastName, String email, int age) {
        StudentRecord studentRecord = new StudentRecord(null, firstName,lastName, email, age, null, null);
        Student student = studentMapper.studentRecordToStudent(studentRecord);
        student.setDateCreated(LocalDateTime.now());

        return studentRepository.save(student);
    }

    @Override
    public Student save(StudentRecord studentRecord) {
        Student student = studentMapper.studentRecordToStudent(studentRecord);
        student.setDateCreated(LocalDateTime.now());

        return studentRepository.save(student);
    }

    @Override
    public List<Student> save(List<StudentRecord> studentRecordList) {
        List<Student> studentList = studentMapper.listStudentRecordsToListStudents(studentRecordList);
        studentList.forEach(student -> student.setDateCreated(LocalDateTime.now()));

        return studentRepository.saveAll(studentList);
    }

    @Override
    public StudentRecord modify(StudentRecord studentRecord) {
        return null;
    }

    @Override
    public void delete(Long id) {}
}
