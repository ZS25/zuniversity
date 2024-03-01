package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.record.StudentRecord;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

public interface StudentControllerGraphQL{

    @QueryMapping(name = "getAllStudents")
    List<StudentRecord> getAll();

    @MutationMapping(name = "addStudentByFields")
    Student save(@Argument String firstName, @Argument String lastName, @Argument String email, @Argument int age);

    @MutationMapping(name = "addStudentRecord")
    Student save(@Argument StudentRecord studentRecord);

    StudentRecord modify(StudentRecord studentRecord);

    void delete(Long id);

}
