package com.example.task_manager_backend.DTO;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetails {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
