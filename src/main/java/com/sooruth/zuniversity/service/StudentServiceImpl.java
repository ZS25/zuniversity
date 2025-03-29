package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public final class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

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
                String.format("Student with ID: %d not found!", id)));
    }

    @Override
    public Student readByEmail(String email) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        return studentOptional.orElseThrow(() -> new IllegalArgumentException(
                String.format("Student with email: %s not found!", email)));
    }

    /**
     * @param page
     * @param size
     * @return List of Students sorted by firstName and then lastName
     */
    @Override
    public Page<Student> readAll(int page, int size) {
        final Sort SORT_BY_FIRST_AND_LAST_NAME = Sort.by("firstName").ascending()
                .and(Sort.by("lastName").ascending());
        PageRequest pageRequest = PageRequest.of(page, size, SORT_BY_FIRST_AND_LAST_NAME);

        return studentRepository.findAll(pageRequest);
    }

    @Override
    public void update(Student student) {
        Student studentFromDatabase = read(student.getId());

        studentFromDatabase.setFirstName(student.getFirstName());
        studentFromDatabase.setLastName(student.getLastName());
        studentFromDatabase.setEmail(student.getEmail());
        studentFromDatabase.setAge(student.getAge());

        studentRepository.save(studentFromDatabase);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> readByAgeOlderThan(int page, int size, Integer age) {
        final Sort SORT_BY_FIRST_AND_LAST_NAME = Sort.by("firstName").ascending()
                .and(Sort.by("lastName").ascending());

        PageRequest pageRequest = PageRequest.of(page, size, SORT_BY_FIRST_AND_LAST_NAME);

        return studentRepository.findAllByAgeAfter(age, pageRequest);
    }
}
