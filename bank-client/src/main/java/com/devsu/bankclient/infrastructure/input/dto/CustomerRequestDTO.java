package com.devsu.bankclient.infrastructure.input.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @Min(value = 0, message = "Age must be a positive number")
    private Integer age;

    @NotBlank(message = "Identification cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Identification must be alphanumeric")
    private String identification;

    private String address;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone must be a valid phone number")
    private String phone;

    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

}
