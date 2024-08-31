package com.cashrich.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequestDto {

    @NotEmpty(message = "First Name can't be empty")
    private String firstName;

    private String lastName;

    @Email(message = "email is not valid")
    private String email;

    @NotEmpty(message = "First Name can't be empty")
    private String mobile;

    @NotEmpty(message = "Password can't be empty")
    private String userName;

    @NotEmpty(message = "Password can't be empty")
    private String password;
}
