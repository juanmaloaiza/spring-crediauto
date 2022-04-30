package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditDto {

    @NotNull(message = "fecha aplicacion requerida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateApplication;

    @NotNull(message = "cliente requerido")
    private CustomerDto customer;

    @NotNull(message = "patio requerido")
    private YardDto yard;

    @NotNull (message = "duracion meses requerida")
    private Integer monthDuration;

    @NotNull(message = "numero pagos requerido")
    private Integer numberFees;

    @NotNull(message = "primer pago requerido")
    private Double firstFee;

    @NotNull(message = "ejecutivo requerido")
    private Executive executive;

    @NotNull(message = "vehiculo requerido")
    private VehicleDto vehicle;

    @NotNull
    private String notes;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusCredit statusCredit;

    public CreditDto(Credit creditEntity) {
        this.dateApplication = creditEntity.getDateApplication();
        this.customer = new CustomerDto(creditEntity.getCustomer());
        this.yard = new YardDto(creditEntity.getYard());
        this.monthDuration = creditEntity.getMonthDuration();
        this.numberFees = creditEntity.getNumberFees();
        this.firstFee = creditEntity.getFirstFee();
        this.executive = creditEntity.getExecutive();
        this.vehicle = new VehicleDto(creditEntity.getVehicle());
        this.notes = creditEntity.getNotes();
        this.statusCredit = creditEntity.getStatusCredit();
    }

    public Credit toCreditEntity ()
    {
        Credit creditEntity = new Credit();
        creditEntity.setDateApplication(getDateApplication());
        creditEntity.setCustomer(getCustomer().toCustomer());
        creditEntity.setYard(getYard().toYard());
        creditEntity.setMonthDuration(getMonthDuration());
        creditEntity.setNumberFees(getNumberFees());
        creditEntity.setFirstFee(getFirstFee());
        creditEntity.setExecutive(getExecutive());
        creditEntity.setVehicle(getVehicle().toVehicle());
        creditEntity.setNotes(getNotes());
        creditEntity.setStatusCredit(getStatusCredit());
        return creditEntity;
    }
}
