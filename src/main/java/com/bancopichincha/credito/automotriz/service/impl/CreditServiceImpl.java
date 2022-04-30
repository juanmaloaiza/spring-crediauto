package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Credit;
import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.domain.StatusVehicle;
import com.bancopichincha.credito.automotriz.domain.Yard;
import com.bancopichincha.credito.automotriz.dto.*;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.repository.CreditRepository;
import com.bancopichincha.credito.automotriz.repository.CustomerRepository;
import com.bancopichincha.credito.automotriz.repository.CustomerYardRepository;
import com.bancopichincha.credito.automotriz.repository.YardRepository;
import com.bancopichincha.credito.automotriz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CreditServiceImpl implements CreditService {

    private CreditRepository creditRepository;
    private ExecutiveService executiveService;
    private CustomerYardRepository customerYardRepository;

    private CustomerRepository customerRepository;

    private YardRepository yardRepository;

    private CustomerService customerService;

    private VehicleService vehicleService;

    public CreditServiceImpl(CreditRepository creditRepository, ExecutiveService executiveService,
                             CustomerYardRepository customerYardRepository, CustomerRepository customerRepository,
                             YardRepository yardRepository, CustomerService customerService,
                             VehicleService vehicleService) {
        this.creditRepository = creditRepository;
        this.executiveService = executiveService;
        this.customerYardRepository = customerYardRepository;
        this.customerRepository = customerRepository;
        this.yardRepository = yardRepository;
        this.customerService = customerService;
        this.vehicleService = vehicleService;
    }

    @Override
    public List<CreditDto> getAllCustomers() {
        return creditRepository.findAll()
                .stream()
                .map(CreditDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CreditDto> findById(Long creditId) {
        return creditRepository.findById(creditId).map(CreditDto::new);
    }

    @Override
    public CreditDto addCredit(CreditDto creditDto) throws BadRequestException, DataAssociateException,
            DataDuplicateException, NotFoundException {
        validateSameVehicule(creditDto.getVehicle());
        validteSameCustormerForDay(creditDto);
        createCustomerYard(creditDto);
        updateStatusVehicle(creditDto.getVehicle());
        Optional<CustomerDto> customer = customerService.findByCustomerId(creditDto.getCustomer().getId());
        if (!customer.isPresent())
            throw  new BadRequestException("No existe cliente");

        creditDto.setCustomer(customer.get());
        Credit credit= creditRepository.save(creditDto.toCreditEntity());
        return new CreditDto(credit);

    }

    private void validateSameVehicule (VehicleDto vehicleDto) throws BadRequestException, DataAssociateException {
        Optional<VehicleDto> dto = vehicleService.findById(vehicleDto.toVehicle().getId());
        if (!dto.isPresent())
            throw new BadRequestException("no existe vehiculo");
        //validar que el vehiculo no este reservado
        if (dto.get().getStatus().equals(StatusVehicle.RESERVADO))
            throw  new DataAssociateException("El vehiculo ya se encuentra reservado");
    }

    private void validteSameCustormerForDay (CreditDto creditDto) throws DataDuplicateException {
        List<Credit> credits = creditRepository.findByDateApplicationAndCustomer(
                creditDto.getDateApplication(), creditDto.getCustomer().toCustomer()
        );
        if (!credits.isEmpty())
            throw new DataDuplicateException("Cliente ya posee solicitud de credito");
    }

    private void createCustomerYard (CreditDto creditDto) throws DataDuplicateException, BadRequestException {
        CustomerYardDto customerYardDto = new CustomerYardDto();
        Optional<Customer> customerEntity = customerRepository.findById(creditDto.getCustomer().getId());
        if (!customerEntity.isPresent())
            throw  new BadRequestException("no existe cliente");
        customerYardDto.setCustomer(new CustomerDto(customerEntity.get()));

        Optional<Yard> yardDto = yardRepository.findById(creditDto.getYard().getId());
         if (!yardDto.isPresent())
            throw new BadRequestException("no existe patio");

        customerYardDto.setYard(new YardDto(yardDto.get()));
        if (customerYardRepository.existsByCustomerId(customerYardDto.getCustomer().getId(), customerYardDto.getYard().getId()))
            throw new DataDuplicateException("Ya existe el cliente en el patio");

        customerYardRepository.save(customerYardDto.toCustomerYard());
    }
    private void updateStatusVehicle (VehicleDto dto) throws NotFoundException, DataAssociateException
    {
        Optional<VehicleDto> vehicleFind = vehicleService.findById(dto.toVehicle().getId());
        if (!vehicleFind.isPresent())
            throw new NotFoundException("El vehiculo no existe");
        VehicleDto updateVehicle=  vehicleFind.get();
        updateVehicle.setStatus(StatusVehicle.RESERVADO);
        vehicleService.updateVehicle(updateVehicle.toVehicle().getId(), updateVehicle);
    }
}
