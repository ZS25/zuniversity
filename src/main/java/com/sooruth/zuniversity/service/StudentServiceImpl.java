package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.exception.ZuniversityRuntimeException;
import com.sooruth.zuniversity.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public final class StudentServiceImpl implements StudentService {

    private final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

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

        return studentOptional.orElseThrow(() -> new ZuniversityRuntimeException(
                String.format("Student with ID:%d not found!", id)));
    }

    @Override
    public Student findStudentByEmail(String email) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        return studentOptional.orElseThrow(() -> new ZuniversityRuntimeException(
                String.format("Student with email:%s not found!", email)));
    }

    /**
     * @param page
     * @param size
     * @return List of Students sorted by firstName and then lastName
     */
    @Override
    public Page<Student> readAll(int page, int size) {
        LOG.info("Inside readAll method!");
        final Sort SORT_BY_FIRST_AND_LAST_NAME = Sort.by("firstName").ascending()
                .and(Sort.by("lastName").ascending());
        PageRequest pageRequest = PageRequest.of(page, size, SORT_BY_FIRST_AND_LAST_NAME);

        return studentRepository.findAll(pageRequest);
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
        return studentRepository.findAllByAgeAfter(age);
    }
}
