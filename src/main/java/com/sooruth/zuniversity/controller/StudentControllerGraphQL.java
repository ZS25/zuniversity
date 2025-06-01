package com.sooruth.zuniversity.controller;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.record.StudentRecord;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

public sealed interface StudentControllerGraphQL permits StudentControllerGraphQLImpl{

    //TODO: add @Valid for the parameters.

    @QueryMapping(name = "getAllStudents")
    List<StudentRecord> getAll();

    @MutationMapping(name = "addStudentByFields")
    Student save(@Argument String firstName, @Argument String lastName, @Argument String email, @Argument int age);

    @MutationMapping(name = "addStudentRecord")
    Student save(@Argument StudentRecord studentRecord);

    @MutationMapping(name = "addListStudentRecord")
    List<Student> save(@Argument List<StudentRecord> studentRecordList);

    void modify(StudentRecord studentRecord);

    void delete(Long id);

}
