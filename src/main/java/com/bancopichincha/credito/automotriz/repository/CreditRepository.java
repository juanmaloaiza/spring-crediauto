package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.domain.Credit;
import com.bancopichincha.credito.automotriz.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditRepository  extends JpaRepository <Credit, Long> {

    Boolean existsByVehicleId (Long vehicleId);

    List<Credit> findByDateApplicationAndCustomer (LocalDate dateApplication, Customer customer);
}
