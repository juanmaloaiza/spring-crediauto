package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.domain.CivilStatus;
import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.CustomerNotFoundException;
import com.bancopichincha.credito.automotriz.repository.CustomerRepository;
import com.bancopichincha.credito.automotriz.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setDocumentNumber("1103896799");
        customer.setAddress("Prueba");
        customer.setFirstName("Juan Manuel");
        customer.setLastName("Loaiza");
        customer.setAge(25);
        customer.setPhone("0989878787");
        customer.setFullNameWife("Esposa");
        customer.setDocumentNumberWife("1111");
        customer.setCivilStatus(CivilStatus.CASADO);
        customer.setBirthDate(LocalDate.now());
    }

    @DisplayName("Listar todos")
    @Test
    void canFindAll ()
    {
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        assertNotNull(customerService.getAllCustomers());
    }

    @Test
    @DisplayName("listar un elemento")
    void canFindById ()
    {
        when (customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        assertNotNull(customerService.findByCustomerId(1L));
    }
    @Test
    void save () throws BadRequestException {
        when (customerRepository.save(any(Customer.class))).thenReturn(customer);
        assertNotNull(customerService.addCustomer(new CustomerDto()));
    }

    @Test
    @DisplayName("validar que retorne la excepcion cuando se repite el documento")
    void shouldThrowBadRequestWhenDuplicateDocumentNumber ()
    {
        CustomerDto dto = new CustomerDto();
        dto.setDocumentNumber("1103896799");
        when(customerRepository.existsByDocumentNumber("1103896799")).thenReturn(true);
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                () -> customerService.addCustomer(dto));
        Assertions.assertEquals("Bad Request Exception", exception.getMessage());
    }

    @Test
    void canUpdate () throws CustomerNotFoundException {
        CustomerDto dto = new CustomerDto();
        dto.setDocumentNumber("1103896799");

        when (customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        when (customerRepository.save(any(Customer.class))).thenReturn(customer);
        assertNotNull(customerService.updateCustomer(1L,dto));
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionWhenIdNotExist ()
    {
        CustomerDto dto = new CustomerDto();
        dto.setDocumentNumber("1103896799");
        when (customerRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        CustomerNotFoundException exception =  Assertions.assertThrows(CustomerNotFoundException.class,
                () -> customerService.updateCustomer(1L,dto));
        Assertions.assertEquals("Cliente no existe", exception.getMessage());

    }

    @Test
    void canDelete ()
    {
        when (customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));


    }





}