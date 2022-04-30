package com.bancopichincha.credito.automotriz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table (name = "cr_vehiculos")
@SQLDelete(sql = "UPDATE cr_vehiculos SET veh_eliminado=true where id=?")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle extends BaseEntity implements Serializable {

    @NotNull
    @Column(name = "veh_placa",  length = 10)
    private String shield;

    @Column(name = "veh_modelo", length = 30)
    @NotNull
    private String model;

    @Column(name = "veh_chasis")
    @NotNull
    private String chasisNumber;

    @Column(name = "veh_tipo")
    private String type;

    @NotNull
    @Column(name = "veh_cilindraje")
    private String cilindraje;

    @NotNull
    @Column(name = "veh_avaluo")
    private Double avaluo;

    @NotNull
    @Column(name = "veh_anio")
    private Integer year;

    @NotNull
    @Column(name = "veh_estado")
    @Enumerated(EnumType.STRING)
    private StatusVehicle status;

    @Column(name="veh_eliminado")
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "veh_fk_marca", referencedColumnName = "id")
    private Brand brand;



}
