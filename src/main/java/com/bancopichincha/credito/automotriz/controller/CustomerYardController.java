package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.dto.CustomerYardDto;
import com.bancopichincha.credito.automotriz.exception.*;
import com.bancopichincha.credito.automotriz.service.CustomerYardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerYardController.CUSTOMERYARD)
public class CustomerYardController  {

    public static final String CUSTOMERYARD="/clientePatios";

    private CustomerYardService customerYardService;

    public CustomerYardController(CustomerYardService customerYardService) {
        this.customerYardService = customerYardService;
    }


    @PostMapping
    public ResponseEntity<CustomerYardDto> save (@Validated @RequestBody CustomerYardDto customerYardDto)
            throws BadRequestException, DataDuplicateException {
        return customerYardService.add(customerYardDto)
                .map(cy -> new ResponseEntity<>(cy,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("{customer-id}")
    public ResponseEntity<CustomerYardDto> update (@PathVariable (value = "customer-id") Long customerYardId,
                                                   @Validated @RequestBody CustomerYardDto customerYardDto)
            throws NotFoundException, DataDuplicateException {
        return customerYardService.update(customerYardId,customerYardDto)
                .map(yard -> new ResponseEntity<>(yard, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("veh-id")
    public ResponseEntity<?> deleteById (@PathVariable (value = "veh-id") Long customerYardId)
            throws NotFoundException, DataAssociateException {
        customerYardService.delete(customerYardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
