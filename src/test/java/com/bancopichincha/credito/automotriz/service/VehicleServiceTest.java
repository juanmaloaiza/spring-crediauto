package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.domain.Brand;
import com.bancopichincha.credito.automotriz.domain.StatusVehicle;
import com.bancopichincha.credito.automotriz.domain.Vehicle;
import com.bancopichincha.credito.automotriz.dto.BrandDto;
import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.dto.VehicleDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.CustomerNotFoundException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.repository.CreditRepository;
import com.bancopichincha.credito.automotriz.repository.VehicleRepository;
import com.bancopichincha.credito.automotriz.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private CreditRepository creditRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    private Vehicle vehicle;
    @BeforeEach
    void setUp() {
        vehicle = new Vehicle();
        vehicle.setYear(2002);
        vehicle.setType("automovil");
        vehicle.setStatus(StatusVehicle.INGRESADO);
        vehicle.setCilindraje("2.0");
        vehicle.setModel("elantra");
        vehicle.setBrand(new Brand("Honda"));
        vehicle.setAvaluo(2000.00);
        vehicle.setChasisNumber("00078787");
        vehicle.setShield("ABC8976");
    }

    @Test
    @DisplayName("Listar todos los vehiculos")
    void canGetAllVehicle() {
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));
        assertNotNull(vehicleService.getAllVehicle());
    }

    @Test
    @DisplayName("Buscar vehiculo por id")
    void canFindById() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.ofNullable(vehicle));
        assertNotNull(vehicleService.findById(1L));
    }

    @Test
    @DisplayName("guardar un vehiculo")
    void addVehicle() throws BadRequestException {
        VehicleDto dto = new VehicleDto();
        dto.setShield("AAA1328");
        dto.setBrand(new BrandDto(1L,"KIA"));
        when(vehicleRepository.existsByShield("AAA1328")).thenReturn(true);
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);
        assertTrue(vehicleService.addVehicle(dto).isPresent());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenIdVehicleNotExist ()
    {
        VehicleDto dto = new VehicleDto();
        dto.setShield("ABA5787");
        when (vehicleRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        BadRequestException exception =  Assertions.assertThrows(BadRequestException.class,
                () -> vehicleService.addVehicle(dto));
        Assertions.assertEquals("Bad Request Exception", exception.getMessage());

    }

    @Test
    void updateVehicle() throws NotFoundException, DataAssociateException {

        VehicleDto dto = new VehicleDto();
        dto.setShield("ABA5787");
        when(vehicleRepository.findById(1L)).thenReturn(Optional.ofNullable(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);
        assertNotNull(vehicleService.updateVehicle(1L,dto));
    }


}