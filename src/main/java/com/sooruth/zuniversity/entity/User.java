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

@Entity(name = "User")
@Table(name = "T_USER", uniqueConstraints = {
        @UniqueConstraint(name = "T_USER_EMAIL_UNIQUE", columnNames = "EMAIL")
})
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "PASSWORD", nullable = false, columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(name = "AUTHORITY", nullable = false, columnDefinition = "VARCHAR(50)")
    private String authority;

    @Column(name = "DATE_CREATED", nullable = false, updatable = false, columnDefinition="TIMESTAMP")
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_UPDATED", columnDefinition="TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime dateUpdated;

    public User() {
    }

    public User(String email, String password, String authority, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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
