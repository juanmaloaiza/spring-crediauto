package com.bancopichincha.credito.automotriz.controller;


import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.dto.CustomerResponseDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.CustomerNotFoundException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping (CustomerController.CUSTOMER)
@Slf4j
public class CustomerController {

    public static final String CUSTOMER ="/clientes";
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> findAll ()
    {
        log.debug("busqueda de clientes Endpoint GET: /api/clientes");
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);

    }

    @PostMapping
    public CustomerDto save (@Validated @RequestBody CustomerDto customerDto)
            throws BadRequestException {
        return this.customerService.addCustomer(customerDto);

    }

    @PutMapping("{customer-id}")
    public CustomerDto update (@PathVariable (value = "customer-id") Long customerId,
                               @Validated @RequestBody CustomerDto customerDto) throws CustomerNotFoundException {
        return customerService.updateCustomer(customerId,customerDto);
    }

    @DeleteMapping("{customer-id}")
    public void deleteById (@PathVariable (value="customer-id") Long customerId) throws DataAssociateException, CustomerNotFoundException {
        this.customerService.deleteCustomer(customerId);
    }




}
