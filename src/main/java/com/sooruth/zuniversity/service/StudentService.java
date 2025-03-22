package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.Student;

import java.util.List;

public sealed interface StudentService extends EntityService<Student> permits StudentServiceImpl{

    List<Student> findAllStudentsOlderThan(Integer age);

    Student findStudentByEmail(String email);
}
