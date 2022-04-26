package com.bancopichincha.credito.automotriz.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table (name="cr_clientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {


    @Id
    @Column(name = "cli_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column (name = "cli_documento", length = 15, nullable = false, unique = true)
    private String documentNumber;

    @NotBlank
    @Column (name = "cli_nombres", length = 50)
    @Size (max = 50, message = "nombre demasiado largo")
    private String firstName;

    @Column (name = "cli_apellidos", length = 50)
    @NotBlank
    private String lastName;

    @Column (name = "cli_fechaNacimiento")
    private LocalDate birthDate;

    @Transient
    private Integer age;

    @Column (name = "cli_direccion", length = 100)
    @NotBlank
    private String address;

    @Column (name = "cli_telefono", length = 20)
    @NotBlank
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column (name = "cli_estadoCivil")
    private CivilStatus civilStatus;

    @Column (name = "cli_documentoY", length = 15)
    @NotBlank
    private String documentNumberWife;

    @Column (name = "cli_nombresY", length = 100)
    @NotBlank
    private String fullNameWife;

    public Customer (String documentNumber, String firstName, String lastName)
    {
        this.documentNumber= documentNumber;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public Customer(String documentNumber, String firstName, String lastName, LocalDate birthDate,
                    String address, String phone, CivilStatus civilStatus, String documentNumberWife,
                    String fullNameWife) {
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.civilStatus = civilStatus;
        this.documentNumberWife = documentNumberWife;
        this.fullNameWife = fullNameWife;
        this.age=10;
    }

    public Integer getAge() {
        return Period.between(LocalDate.now(), this.getBirthDate()).getYears();
    }
}
