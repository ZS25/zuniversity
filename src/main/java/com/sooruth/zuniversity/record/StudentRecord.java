package com.sooruth.zuniversity.record;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public record StudentRecord(

        Long id,
        @NotBlank(message = "firstName cannot be blank")
        @Size(min = 2, max = 30)
        String firstName,
        @NotBlank(message = "lastName cannot be blank")
        @Size(min = 2, max = 30)
        String lastName,
        @NotBlank(message = "email cannot be blank")
        @Size(min = 5, max = 30)
        @Email(message = "Email should be valid")
        String email,
        @Min(value = 18, message = "Age should not be less than 18")
        @Max(value = 150, message = "Age should not be greater than 150")
        int age,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated) {
}
