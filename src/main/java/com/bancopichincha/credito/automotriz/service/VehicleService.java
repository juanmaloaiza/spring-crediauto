package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.domain.Vehicle;
import com.bancopichincha.credito.automotriz.dto.VehicleDto;
import com.bancopichincha.credito.automotriz.dto.YardDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<VehicleDto> getAllVehicle();

    List<VehicleDto> findByBrandOrModelOrYear(String brandName, String model, Integer year);

    Optional<VehicleDto> findById (Long vehicleId);

    Optional<VehicleDto> addVehicle (VehicleDto vehicleDto) throws BadRequestException;

    Optional <VehicleDto> updateVehicle (Long vehicleId, VehicleDto vehicleDto) throws NotFoundException, DataAssociateException;

    void deleteVehicle (Long yardId) throws NotFoundException, DataAssociateException;
}
