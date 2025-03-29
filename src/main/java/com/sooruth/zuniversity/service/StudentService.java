package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.Student;
import org.springframework.data.domain.Page;

public sealed interface StudentService extends EntityService<Student> permits StudentServiceImpl{

    Page<Student> readByAgeOlderThan(int page, int size, Integer age);

    Student readByEmail(String email);
}
