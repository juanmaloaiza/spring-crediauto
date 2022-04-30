package com.bancopichincha.credito.automotriz.dto;


import com.bancopichincha.credito.automotriz.domain.StatusVehicle;
import com.bancopichincha.credito.automotriz.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto {

    private Long id;
    @NotNull (message = "placa requerida")
    private String shield;

    @NotNull(message = "modelo requerido")
    private String model;

    @NotNull(message = "chasis requerido")
    private String chasisNumber;

    private String type;
    @NotNull(message = "cilindraje requerido")
    private String cilindraje;
    @NotNull(message = "avaluo requerido")
    private Double avaluo;
    @NotNull(message = "año requerido")
    private Integer year;

    @NotNull (message = "estado requerido")
    @Enumerated(EnumType.STRING)
    private StatusVehicle status;

    @NotNull
    private BrandDto brand;

    public VehicleDto(Vehicle vehicle) {
        this.id=vehicle.getId();
        this.shield = vehicle.getShield();
        this.model = vehicle.getModel();
        this.chasisNumber = vehicle.getChasisNumber();
        this.type = vehicle.getType();
        this.cilindraje = vehicle.getCilindraje();
        this.avaluo = vehicle.getAvaluo();
        this.year = vehicle.getYear();
        this.status = vehicle.getStatus();
        this.brand = new BrandDto(vehicle.getBrand());
    }

    public Vehicle toVehicle ()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(getId());
        vehicle.setShield(getShield());
        vehicle.setModel(getModel());
        vehicle.setChasisNumber(getChasisNumber());
        vehicle.setType(getType());
        vehicle.setCilindraje(getCilindraje());
        vehicle.setYear(getYear());
        vehicle.setAvaluo(getAvaluo());
        vehicle.setStatus(getStatus());
        vehicle.setBrand(getBrand().toBrand());
        return vehicle;

    }
}
