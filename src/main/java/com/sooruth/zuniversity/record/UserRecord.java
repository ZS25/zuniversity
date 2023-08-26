package com.sooruth.zuniversity.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UserRecord(
        Long id,
        @NotBlank(message = "email cannot be blank")
        @Size(min = 5, max = 50)
        @Email(message = "Email should be valid")
        String email,
        @NotBlank(message = "password cannot be blank")
        @Size(min = 2, max = 50)
        String password,
        @NotBlank(message = "authority cannot be blank")
        @Size(min = 2, max = 50)
        String authority,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated) {
}
