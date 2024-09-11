package com.example.jumlacycle.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {
    private Integer customerId;
    @NotEmpty(message = "username can not be null")
    private String username;
    @Pattern(regexp = "^05\\d{8}$",message = "Phone number most be as 05XXXXXXXX")
    private String phoneNumber;

    @Email(message = "Enter valid email")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$", message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;

    @Pattern(regexp = "(CUSTOMER)")
    private String role;

}
