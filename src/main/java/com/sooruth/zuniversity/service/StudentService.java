package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.Student;

import java.util.List;

public interface StudentService extends EntityService<Student>{

    List<Student> findAllStudentsOlderThan(Integer age);

    Student findStudentByEmail(String email);
}
