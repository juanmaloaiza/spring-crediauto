package com.bancopichincha.credito.automotriz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table (name = "cr_marcas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Brand  extends BaseEntity implements Serializable {

    @NotNull
    @Column(name = "mar_nombre")
    private String name;

}
