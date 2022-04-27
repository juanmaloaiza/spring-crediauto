package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.Executive;
import com.bancopichincha.credito.automotriz.domain.Yard;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class ExecutiveDto extends PersonDto {

    @NotNull (message = "Celular requerido")
    @Pattern(regexp = "", message = "Formato incorrecto")
    private String cellPhone;
    @NotNull (message = "Celular requerido")
    private Yard yard;

    public ExecutiveDto(Executive executive) {
        super (executive.getDocumentNumber(),executive.getFirstName(), executive.getLastName(), executive.getAge(),
                executive.getAddress(), executive.getPhone());
        this.cellPhone = executive.getCellPhone();
        this.yard = executive.getYard();
    }

    public Executive toExecutive ()
    {
        Executive executive = new Executive();
        executive.setFirstName(this.getFirstName());
        executive.setLastName(this.getLastName());
        executive.setDocumentNumber(this.getDocumentNumber());
        executive.setAge(this.getAge());
        executive.setAddress(this.getAddress());
        executive.setPhone(this.getPhone());
        executive.setCellPhone(this.cellPhone);
        executive.setYard(this.yard);
        return executive;
    }


}
