package com.sooruth.zuniversity.mapper;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.record.StudentRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student studentRecordToStudent(StudentRecord studentRecord);
    StudentRecord studentToStudentRecord(Student student);

    List<Student> listStudentRecordsToListStudents(List<StudentRecord> studentRecordList);
    List<StudentRecord> listStudentsToListStudentRecords(List<Student> studentList);
}
