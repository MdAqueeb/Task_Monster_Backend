package com.example.task_manager_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {
    @NotBlank
    private String email; 
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
