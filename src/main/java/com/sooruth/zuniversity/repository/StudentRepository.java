package com.sooruth.zuniversity.repository;

import com.sooruth.zuniversity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAgeAfter(Integer age);

    @Query("SELECT s FROM Student s WHERE s.email = ?1") //JPQL
    Optional<Student> findStudentByEmail(String email);

    @Query(value = "SELECT * FROM T_STUDENT WHERE FIRST_NAME = ?1 AND AGE >= ?2", nativeQuery = true) //SQL (DB dependent)
    List<Student> findStudentsByFirstNameAndAgeGreaterThanEqual(String firstName, Integer age);
}
