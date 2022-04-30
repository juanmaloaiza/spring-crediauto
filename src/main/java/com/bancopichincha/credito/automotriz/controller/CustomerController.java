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
    public ResponseEntity<CustomerDto> save (@Validated @RequestBody CustomerDto customerDto)
            throws BadRequestException {
        CustomerDto saved = this.customerService.addCustomer(customerDto);

        if (saved==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(saved,HttpStatus.CREATED);

    }

    @PutMapping("{customer-id}")
    public ResponseEntity<CustomerDto> update (@PathVariable (value = "customer-id") Long customerId,
                               @Validated @RequestBody CustomerDto customerDto) throws CustomerNotFoundException {
        CustomerDto updated= customerService.updateCustomer(customerId,customerDto);
        if (updated==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @DeleteMapping("customer-id")
    public ResponseEntity<?> deleteById (@PathVariable (value = "customer-id") Long yardId)
            throws  DataAssociateException, CustomerNotFoundException {
        customerService.deleteCustomer(yardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
