package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Brand;
import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.dto.CustomerResponseDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.CustomerNotFoundException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.helper.CSVHelper;
import com.bancopichincha.credito.automotriz.repository.CustomerRepository;
import com.bancopichincha.credito.automotriz.repository.CustomerYardRepository;
import com.bancopichincha.credito.automotriz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerYardRepository customerYardRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerYardRepository customerYardRepository) {
        this.customerRepository = customerRepository;
        this.customerYardRepository = customerYardRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDto> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(CustomerResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDto> findByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).map(CustomerDto::new);
    }

    @Override
    @Transactional
    public CustomerDto addCustomer(CustomerDto customerDto) throws BadRequestException {
        if (customerRepository.existsByDocumentNumber(customerDto.getDocumentNumber()))
            throw new BadRequestException("Ya existe el cliente");
        Customer saved= customerRepository.save(customerDto.toCustomer());
        return  new CustomerDto(saved);
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException, DataAssociateException {
        if (!customerRepository.existsById(customerId))
            throw new CustomerNotFoundException();

        //Buscar en la entidad YardCustomer para verificar relaciones
        if (customerYardRepository.existsByCustomerId(customerId))
            throw new DataAssociateException("Cliente con info asociada");


        customerRepository.deleteById(customerId);
    }

    @Override
    public void loadData(File file) throws DataDuplicateException, BadRequestException {
        //validate format
        if (!CSVHelper.isCSVFile(file))
            throw new BadRequestException("Formato incorrecto");

        try {
            List<Customer> customerList = CSVHelper.parseCustomerCvsFile(new FileInputStream(file));
            //validate duplicados
            Set<Customer> duplicates = CSVHelper.findDuplicateBySetAdd(customerList);
            if (!duplicates.isEmpty())
                throw new DataDuplicateException("clientes duplicados: " + duplicates.toString());

            customerRepository.saveAll(customerList);
        }
        catch (IOException ioException)
        {
            throw new RuntimeException("error con datos cvs: " + ioException.getMessage());
        }
    }
}
