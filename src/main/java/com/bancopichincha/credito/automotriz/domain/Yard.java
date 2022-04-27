package com.bancopichincha.credito.automotriz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "cr_patios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Yard extends BaseEntity implements Serializable {

    @NotNull
    @Column(name = "pat_nombre", length = 50)
    private String name;

    @NotNull
    @Column(name = "pat_direccion", length = 100)
    private String addres;

    @NotNull
    @Column(name = "pat_telefono", length = 20)
    private String phone;

    @NotNull
    @Column(name = "pat_punto_venta", length = 20)
    private Integer numberPointOfSale;

}
