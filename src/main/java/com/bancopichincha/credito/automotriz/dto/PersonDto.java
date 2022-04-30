package com.bancopichincha.credito.automotriz.dto;

import com.opencsv.bean.CsvBindByPosition;
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

    private Long id;

    @NotNull (message = "Numero documento requerido")
    @CsvBindByPosition(position = 0)
    private String documentNumber;

    @NotNull (message = "nombres son requerido")
    @CsvBindByPosition(position = 1)
    private String firstName;

    @NotNull (message = "apellidos son requerido")
    @CsvBindByPosition(position = 2)
    private String lastName;

    @NotNull(message = "edad requerido")
    @CsvBindByPosition(position = 3)
    private Integer age;

    @NotNull(message = "direccion requerida")
    @CsvBindByPosition(position = 4)
    private String address;

    @NotNull (message = "telefono requerido")
    @CsvBindByPosition(position = 5)
    private String phone;
}
