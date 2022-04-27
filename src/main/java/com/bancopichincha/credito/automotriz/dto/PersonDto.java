package com.bancopichincha.credito.automotriz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto {

    @NotNull (message = "Numero documento requerido")
    private String documentNumber;

    @NotNull (message = "nombres son requerido")
    private String firstName;

    @NotNull (message = "nombres son requerido")
    private String lastName;

    private Integer age;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;
}
