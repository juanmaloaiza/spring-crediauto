package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.dto.CustomerResponseDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.CustomerNotFoundException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface CustomerService {

    List<CustomerResponseDto> getAllCustomers();

    Optional <CustomerDto> findByCustomerId (Long customerId);
    CustomerDto addCustomer (CustomerDto customer) throws BadRequestException;

    CustomerDto updateCustomer (Long customerId, CustomerDto customer) throws  CustomerNotFoundException;

    void deleteCustomer (Long customerId) throws CustomerNotFoundException, DataAssociateException;
}
