package com.lvprasad.springbootwebapplication.springbootwebapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lvprasad.springbootwebapplication.springbootwebapp.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    //Annotion of validation (you can google for business use case)
    @NotNull(message = "Required field in Employee: name")
    @NotEmpty(message = "Name of the the Employee cannot be Empty: name")
    @NotBlank(message = "Name of the the Employee cannot be Blank: name")
    @Size(min=3, max=10, message="Number of Characters should be in this range only:[3,10]")
    private String name;

    @NotBlank(message = "email of the the Employee cannot be Blank: email")
    @Email(message = "email should be valid email")
    private String email;

    @Max(value = 80, message = "Age of Employee cannot be greater than 80")
    @Min(value = 18 , message = "Age of Employee cannot be less than 18")
    private Integer age;

    @NotBlank(message = "role of the the Employee cannot be Blank: role")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of the employee is ADMIN or USER")
    @EmployeeRoleValidation
    private String role;


    @NotNull(message = "Salary of Employee should be not null")
    @Positive(message = "Salary of the employee should be positive")
//    @PositiveOrZero
//    @NegativeOrZero
    @Digits(integer = 6, fraction = 2, message = "salary cannot be xxxxx.yy")
    @DecimalMax(value="100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

//    @Past("")

//    @Future
    private LocalDate dateOfBirth;

    @PastOrPresent(message = "Date of Joining field in the Employee cannot be the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message="Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;


}
