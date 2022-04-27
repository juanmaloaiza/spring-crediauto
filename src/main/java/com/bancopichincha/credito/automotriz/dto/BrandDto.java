package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandDto {

    @NotBlank
    private String name;

    public BrandDto (Brand brand)
    {
        this.name=brand.getName();
    }

    public Brand toBrand()
    {
        Brand brand = new Brand();
        brand.setName(this.getName());
        return brand;
    }


}
