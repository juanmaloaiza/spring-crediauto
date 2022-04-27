package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.domain.CivilStatus;
import com.bancopichincha.credito.automotriz.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    //todo mock repository
    private CustomerRepository underTest;

    @AfterEach
    void tearDown ()
    {
        underTest.deleteAll();
    }

    @Test
    void existsByDocumentNumber() {
        //todo TDD probar metodo
    }




}