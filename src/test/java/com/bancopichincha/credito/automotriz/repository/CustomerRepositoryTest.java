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
    private CustomerRepository underTest;

    @AfterEach
    void tearDown ()
    {
        underTest.deleteAll();
    }

    @Test
    void existsByDocumentNumber() {
        //given
        String document="1103896799";
        Customer customer = new Customer( "1103896799","Juan", "Loaiza",LocalDate.now(),
                "Prueba", "098787554",CivilStatus.CASADO,
                "11012124241", "Esposa");
        underTest.save(customer);

        //when
        boolean expected= underTest.existsByDocumentNumber(document);

        //then
        assertThat(expected).isTrue();
    }


}