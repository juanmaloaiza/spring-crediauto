package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.Executive;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExecutiveDto extends PersonDto {

    @NotNull (message = "Celular requerido")
    @CsvBindByPosition(position = 6)
    private String cellPhone;
    @NotNull (message = "patio requerido")
    @CsvBindByPosition(position = 7)
    private YardDto yardDto;

    private Long idYard;

    public ExecutiveDto(Executive executive) {
        super (executive.getId(),executive.getDocumentNumber(),executive.getFirstName(), executive.getLastName(), executive.getAge(),
                executive.getAddress(), executive.getPhone());
        this.cellPhone = executive.getCellPhone();
        this.yardDto = new YardDto(executive.getYard());
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
        //executive.setYard(this.yardDto.toYard());
        return executive;
    }


}
