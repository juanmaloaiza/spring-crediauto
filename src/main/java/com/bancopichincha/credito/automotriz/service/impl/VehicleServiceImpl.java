package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.StatusVehicle;
import com.bancopichincha.credito.automotriz.domain.Vehicle;
import com.bancopichincha.credito.automotriz.dto.VehicleDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.repository.BrandRepository;
import com.bancopichincha.credito.automotriz.repository.CreditRepository;
import com.bancopichincha.credito.automotriz.repository.VehicleRepository;
import com.bancopichincha.credito.automotriz.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private CreditRepository creditRepository;

    private BrandRepository brandRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, CreditRepository creditRepository, BrandRepository brandRepository) {
        this.vehicleRepository = vehicleRepository;
        this.creditRepository = creditRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly =true)
    public List<VehicleDto> getAllVehicle() {
        return vehicleRepository.findAll()
                .stream()
                .map( v -> new VehicleDto(v))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> findByBrandOrModelOrYear(String brandName, String model, Integer year) {
        return vehicleRepository.findByBrandOrModelOrYear(brandName,model,year)
                .stream()
                .map( v -> new VehicleDto(v))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleDto> findById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId).map(VehicleDto::new);
    }

    @Override
    @Transactional
    public Optional<VehicleDto> addVehicle(VehicleDto vehicleDto) throws BadRequestException {
        if (vehicleRepository.existsByShield(vehicleDto.getShield()))
            throw new BadRequestException("Ya existe el vehiculo");
        //get Marca
        Vehicle vehicle = vehicleDto.toVehicle();
        if(!brandRepository.findById(vehicleDto.getBrand().getId()).isPresent())
            throw new BadRequestException("No existe la marca");

        vehicle.setBrand(brandRepository.findById(vehicleDto.getBrand().getId()).get());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return Optional.ofNullable(savedVehicle).map(VehicleDto::new);
    }

    @Override
    @Transactional
    public Optional<VehicleDto> updateVehicle(Long vehicleId, VehicleDto vehicleDto) throws NotFoundException, DataAssociateException {
        Optional <Vehicle> vehicleFind = vehicleRepository.findById(vehicleId);
        if (!vehicleFind.isPresent())
            throw new NotFoundException("No existe vehiculo");
        Vehicle vehicle = vehicleFind.get();
        if (vehicle.getStatus().equals(StatusVehicle.RESERVADO) || vehicle.getStatus().equals(StatusVehicle.VENDIDO))
            throw new DataAssociateException("El vehiculo esta reservado o vendido");

        vehicle= vehicleRepository.save(vehicleDto.toVehicle());
        return Optional.ofNullable(vehicleDto);
    }

    @Override
    @Transactional
    public void deleteVehicle(Long vehicleId) throws NotFoundException, DataAssociateException {
       if (!vehicleRepository.existsById(vehicleId))
           throw new NotFoundException();
       //Buscar en la entidad Credit para verificar relaciones
        if (creditRepository.existsByVehicleId(vehicleId))
            throw new DataAssociateException("vehiculo con información asociada");

        vehicleRepository.deleteById(vehicleId);
    }
}
