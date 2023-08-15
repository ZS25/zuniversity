package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Long create(Student student) {
        student.setDateCreated(LocalDateTime.now());
        return (studentRepository.save(student)).getId();
    }

    @Override
    public Student read(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        return studentOptional.orElseThrow(() -> new IllegalArgumentException(
                String.format("Student with ID:%d not found!", id)));
    }

    @Override
    public Student findStudentByEmail(String email) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        return studentOptional.orElseThrow(() -> new IllegalArgumentException(
                String.format("Student with email:%s not found!", email)));
    }

    @Override
    public List<Student> readAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student update(Student student) {
        Student studentFromDatabase = read(student.getId());

        studentFromDatabase.setFirstName(student.getFirstName());
        studentFromDatabase.setLastName(student.getLastName());
        studentFromDatabase.setEmail(student.getEmail());
        studentFromDatabase.setAge(student.getAge());

        return studentRepository.save(studentFromDatabase);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAllStudentsOlderThan(Integer age) {
        List<Student> studentList = studentRepository.findAllByAgeAfter(age);
        return studentList;
    }
}
