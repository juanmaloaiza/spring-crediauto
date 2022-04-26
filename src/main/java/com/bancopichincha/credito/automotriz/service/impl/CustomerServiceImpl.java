package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.dto.CustomerResponseDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.CustomerNotFoundException;
import com.bancopichincha.credito.automotriz.repository.CustomerRepository;
import com.bancopichincha.credito.automotriz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(customer -> new CustomerResponseDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) throws BadRequestException {
        if (customerRepository.existsByDocumentNumber(customerDto.getDocumentNumber()))
            throw new BadRequestException("Ya existe el cliente");

        Customer customer= customerRepository.save(customerDto.toCustomer());
        return  new CustomerDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerUpdate) throws CustomerNotFoundException {
        Optional<Customer> customerFind = customerRepository.findById(customerId);
        return customerFind
                .map( customer -> {
                    customer=customerUpdate.toCustomer();
                    return customerRepository.save(customer);
                })
                .map(CustomerDto::new)
                .orElseThrow( () -> new CustomerNotFoundException());
    }

    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        customerRepository.deleteById(customerId);
    }
}
