package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.domain.CustomerYard;
import com.bancopichincha.credito.automotriz.domain.Yard;
import com.bancopichincha.credito.automotriz.dto.CustomerYardDto;
import com.bancopichincha.credito.automotriz.exception.*;
import com.bancopichincha.credito.automotriz.repository.CustomerRepository;
import com.bancopichincha.credito.automotriz.repository.CustomerYardRepository;
import com.bancopichincha.credito.automotriz.repository.YardRepository;
import com.bancopichincha.credito.automotriz.service.CustomerYardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerYardServiceImpl implements CustomerYardService {

    private CustomerYardRepository customerYardRepository;
    private CustomerRepository customerRepository;
    private YardRepository yardRepository;

    @Autowired
    public CustomerYardServiceImpl(CustomerYardRepository customerYardRepository, CustomerRepository customerRepository, YardRepository yardRepository) {
        this.customerYardRepository = customerYardRepository;
        this.customerRepository = customerRepository;
        this.yardRepository = yardRepository;
    }

    @Override
    public Optional<CustomerYardDto> add(CustomerYardDto customerYardDto) throws DataDuplicateException, BadRequestException {
        //validar si no esta asignado el cliente
        if (customerYardRepository.existsByCustomerId(customerYardDto.getCustomer().getId(), customerYardDto.getYard().getId()))
            throw new DataDuplicateException("Cliente asignado");
        CustomerYard entityYardCustomer = new CustomerYard();

        Optional<Customer> customerEntity = customerRepository.findById(customerYardDto.getCustomer().getId());
        if (!customerEntity.isPresent())
            throw new BadRequestException("No existe cliente");

        Optional<Yard> yardEntity = yardRepository.findById(customerYardDto.getYard().getId());

        if (!yardEntity.isPresent())
            throw new BadRequestException("No existe patio");


        entityYardCustomer.setCustomer(customerEntity.get());
        entityYardCustomer.setYard(yardEntity.get());
        entityYardCustomer.setDateAssign(customerYardDto.getDateAssign());

        CustomerYard saved = customerYardRepository.save(entityYardCustomer);
        return Optional.ofNullable(saved).map(CustomerYardDto::new);
    }

    @Override
    public Optional<CustomerYardDto> update(Long customerYardId, CustomerYardDto customerYardDto) throws NotFoundException, DataDuplicateException {
        Optional <CustomerYard> customerYardFind = customerYardRepository.findById(customerYardId);

        if (!customerYardFind.isPresent())
            throw new NotFoundException();

        //validar que no exista el cliente
        if (customerYardRepository.existsByCustomerId(customerYardDto.getCustomer().getId(), customerYardDto.getYard().getId()))
            throw new DataDuplicateException("Cliente asignado");

        return customerYardFind
                .map( cy -> {
                    cy=customerYardDto.toCustomerYard();
                    return customerYardRepository.save(cy);
                })
                .map(CustomerYardDto::new);
    }

    @Override
    public void delete(Long customerYardId) throws  NotFoundException {

        if (!customerYardRepository.existsById(customerYardId))
            throw  new NotFoundException("no existe Cliente Patio");

        customerYardRepository.deleteById(customerYardId);

    }
}
