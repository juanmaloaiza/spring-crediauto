package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.Brand;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import com.opencsv.bean.CsvBindByPosition;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandDto {

    private Long id;

    @NotNull(message = "El nombre es requerido")
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "name", required = true)
    private String name;

    public BrandDto (Brand brand)
    {
        this.name=brand.getName();
        this.id= brand.getId();
    }

    public Brand toBrand()
    {
        Brand brand = new Brand();
        brand.setId(this.getId());
        brand.setName(this.getName());
        return brand;
    }


}
