package com.sooruth.zuniversity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Student")
@Table(name = "T_STUDENT", uniqueConstraints = {
        @UniqueConstraint(name = "T_STUDENT_EMAIL_UNIQUE", columnNames = "EMAIL")
})
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, columnDefinition = "VARCHAR(50)")
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, columnDefinition = "VARCHAR(50)")
    private String lastName;

    @Column(name = "EMAIL", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "AGE", nullable = false, columnDefinition = "INTEGER")
    private int age;

    @Column(name = "DATE_CREATED", nullable = false, updatable = false, columnDefinition="TIMESTAMP")
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_UPDATED", columnDefinition="TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime dateUpdated;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
