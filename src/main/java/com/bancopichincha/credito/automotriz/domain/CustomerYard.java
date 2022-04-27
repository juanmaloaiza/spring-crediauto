package com.bancopichincha.credito.automotriz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table (name="cr_clientes_patios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerYard extends BaseEntity implements Serializable {


    @ManyToOne
    @JoinColumn(name = "clp_fk_clientr", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "clp_fk_patio", referencedColumnName = "id")
    private Yard yard;

    @Column (name = "clp_fecha_asignacion")
    private LocalDate dateAssign;
}
