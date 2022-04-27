package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.Yard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class YardDto {

    private String name;

    @NotBlank
    @Column(name = "pat_direccion", length = 100)
    private String addres;

    @NotBlank
    @Column(name = "pat_telefono", length = 20)
    private String phone;

    @NotBlank
    @Column(name = "pat_punto_venta", length = 20)
    private Integer numberPointOfSale;

    public YardDto(Yard yard) {
        this.name = yard.getName();
        this.addres = yard.getAddres();
        this.phone = yard.getPhone();
        this.numberPointOfSale = yard.getNumberPointOfSale();
    }

    public Yard toYard ()
    {
        Yard yard = new Yard();
        yard.setName(this.getName());
        yard.setPhone(this.getPhone());
        yard.setAddres(this.getAddres());
        yard.setNumberPointOfSale(this.getNumberPointOfSale());
        return yard;
    }


}
