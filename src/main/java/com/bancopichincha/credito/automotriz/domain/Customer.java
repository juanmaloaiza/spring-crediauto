package com.bancopichincha.credito.automotriz.domain;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table (name="cr_clientes")
@SQLDelete(sql = "UPDATE cr_clientes SET  cli_eliminado=true WHERE id =?")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends Person implements Serializable {


    @Column (name = "cli_fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

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

    @Column(name = "cli_eliminado")
    private Boolean delete = Boolean.FALSE;


    @Column(name = "cli_sujeto_credito")
    private Boolean verifyCredit = Boolean.TRUE;

    public Customer(String documentNumber, String firstName,
                    String lastName, String address, String phone,
                    Date birthDate, Integer age, CivilStatus civilStatus,
                    String documentNumberWife, String fullNameWife) {
        super(documentNumber, firstName, lastName, address, phone, age);
        this.birthDate = birthDate;
        this.civilStatus = civilStatus;
        this.documentNumberWife = documentNumberWife;
        this.fullNameWife = fullNameWife;
    }

}
