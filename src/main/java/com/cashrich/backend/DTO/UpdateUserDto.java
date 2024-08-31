package com.cashrich.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDto {

    @NotEmpty(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    private  String email;

    private String firstName;

    private String lastName;

    private String mobile;

    private String password;
}
