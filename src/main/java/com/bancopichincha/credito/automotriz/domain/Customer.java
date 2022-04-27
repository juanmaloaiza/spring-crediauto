package com.bancopichincha.credito.automotriz.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table (name="cr_clientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends Person implements Serializable {


    @Column (name = "cli_fechaNacimiento")
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column (name = "cli_estadoCivil")
    private CivilStatus civilStatus;

    @Column (name = "cli_documentoY", length = 15)
    @NotNull
    private String documentNumberWife;

    @Column (name = "cli_nombresY", length = 100)
    @NotNull
    private String fullNameWife;

    public Customer(String documentNumber, String firstName,
                    String lastName, String address, String phone,
                    LocalDate birthDate, Integer age, CivilStatus civilStatus,
                    String documentNumberWife, String fullNameWife) {
        super(documentNumber, firstName, lastName, address, phone, age);
        this.birthDate = birthDate;
        this.civilStatus = civilStatus;
        this.documentNumberWife = documentNumberWife;
        this.fullNameWife = fullNameWife;
    }

}
