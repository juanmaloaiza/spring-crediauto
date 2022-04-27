package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.dto.VehicleDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VehicleController.VEHICLE)
@Slf4j
public class VehicleController {

    public static final String VEHICLE ="/vehiculos";

    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> findAll()
    {
        log.debug("Busqueda de vehiculos Endpoint GET: /api/vehiculos" );
        return new ResponseEntity<>(this.vehicleService.getAllVehicle(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <VehicleDto> save (@Validated @RequestBody VehicleDto vehicleDto) throws BadRequestException {
        return vehicleService.addVehicle(vehicleDto)
                .map(veh -> new ResponseEntity<>(veh,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("{veh-id}")
    public ResponseEntity<VehicleDto> update( @PathVariable(value="veh-id") Long vehicleId,
                                           @Validated @RequestBody VehicleDto vehicleDto) throws NotFoundException {
        return vehicleService.updateVehicle(vehicleId,vehicleDto)
                .map(yard -> new ResponseEntity<>(yard, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("veh-id")
    public ResponseEntity<?> deleteById (@PathVariable (value = "veh-id") Long vehicleId) throws NotFoundException, DataAssociateException {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
