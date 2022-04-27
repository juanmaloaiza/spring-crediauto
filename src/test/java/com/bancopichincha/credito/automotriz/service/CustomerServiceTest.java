package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.domain.CivilStatus;
import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.repository.CustomerRepository;
import com.bancopichincha.credito.automotriz.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {


    @Mock private CustomerRepository customerRepository;
    private CustomerServiceImpl underTest;

    private Customer customer;

    @BeforeEach
    void setUp() {






    }

    @Test
    void canGetAllCustomers() {

        //when
        underTest.getAllCustomers();
        //then
        verify(customerRepository).findAll();

    }

    @Test
    void canSave () throws BadRequestException {
       CustomerDto dto = new CustomerDto(customer);
        //when
        underTest.addCustomer(dto);

        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());
        Customer captureCustomer = customerArgumentCaptor.getValue();
        assertThat(captureCustomer).isEqualTo(customer);
    }






}