package com.bancopichincha.credito.automotriz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name="cr_credito")
public class Credit extends BaseEntity implements Serializable {

    @Column(name = "cre_fecha_aplica")
    private LocalDate dateApplication;

    @ManyToOne
    @JoinColumn (name = "cre_fk_cliente", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "cre_fk_patio", referencedColumnName = "id")
    private Yard yard;

    @Column(name = "cre_duracion_meses")
    private Integer monthDuration;

    @Column(name = "cre_numero_cuotas")
    private Integer numberFees;

    @Column(name="cre_entrada")
    private Double firstFee;

    @ManyToOne
    @JoinColumn (name = "cre_fk_ejecutivo", referencedColumnName = "id")
    private Executive executive;

    @ManyToOne
    @JoinColumn (name = "cre_fk_vehiculo", referencedColumnName = "id")
    private Vehicle vehicle;

    @Column(name="cre_observacion")
    private String notes;

    @Column(name = "cre_estado")
    @Enumerated(EnumType.STRING)
    private StatusCredit statusCredit;

}
