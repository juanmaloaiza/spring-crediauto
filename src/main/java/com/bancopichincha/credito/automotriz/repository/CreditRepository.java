package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.domain.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository  extends JpaRepository <Credit, Long> {

    Boolean existsByVehicleId (Long vehicleId);
}
