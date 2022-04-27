package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Vehicle;
import com.bancopichincha.credito.automotriz.dto.VehicleDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.repository.CreditRepository;
import com.bancopichincha.credito.automotriz.repository.VehicleRepository;
import com.bancopichincha.credito.automotriz.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private CreditRepository creditRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, CreditRepository creditRepository) {
        this.vehicleRepository = vehicleRepository;
        this.creditRepository = creditRepository;
    }

    @Override
    public List<VehicleDto> getAllVehicle() {
        return vehicleRepository.findAll()
                .stream()
                .map( v -> new VehicleDto(v))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VehicleDto> findById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId).map(VehicleDto::new);
    }

    @Override
    public Optional<VehicleDto> addVehicle(VehicleDto vehicleDto) throws BadRequestException {
        if (vehicleRepository.existsByShield(vehicleDto.getShield()))
            throw new BadRequestException("Ya existe el vehiculo");

        Vehicle savedVehicle = vehicleRepository.save(vehicleDto.toVehicle());
        return Optional.ofNullable(savedVehicle).map(VehicleDto::new);
    }

    @Override
    public Optional<VehicleDto> updateVehicle(Long vehicleId, VehicleDto vehicleDto) throws NotFoundException {
        Optional <Vehicle> vehicleFind = vehicleRepository.findById(vehicleId);

        return vehicleFind
                .map(v -> vehicleRepository.save(v))
                .map(VehicleDto::new);

    }

    @Override
    public void deleteVehicle(Long vehicleId) throws NotFoundException, DataAssociateException {
       if (!vehicleRepository.existsById(vehicleId))
           throw new NotFoundException();
       //Buscar en la entidad Credit para verificar relaciones
        if (creditRepository.existsByVehicleId(vehicleId))
            throw new DataAssociateException("vehiculo con información asociada");

        vehicleRepository.deleteById(vehicleId);
    }
}
