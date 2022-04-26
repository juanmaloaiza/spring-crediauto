package com.bancopichincha.credito.automotriz.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cr_patio")
@AllArgsConstructor
@NoArgsConstructor
public class Yard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pat_id")
    private Long id;

    @NotBlank
    @Column(name = "pat_nombre", length = 50)
    private String name;

    @NotBlank
    @Column(name = "pat_direccion", length = 100)
    private String addres;

    @NotBlank
    @Column(name = "pat_telefono", length = 20)
    private String phone;

    @NotBlank
    @Column(name = "pat_punto_venta", length = 20)
    private String numberPointOfSale;

}
