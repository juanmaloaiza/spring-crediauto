package com.bancopichincha.credito.automotriz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table (name = "cr_ejecutivos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Executive extends Person implements Serializable {



    @NotNull
    @Column (name = "eje_celular")
    private String cellPhone;

    @ManyToOne
    @JoinColumn (name="eje_fk_patio", referencedColumnName = "id")
    private Yard yard;

}
