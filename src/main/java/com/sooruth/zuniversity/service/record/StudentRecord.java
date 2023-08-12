package com.sooruth.zuniversity.service.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
        String email,
        int age) {
}
