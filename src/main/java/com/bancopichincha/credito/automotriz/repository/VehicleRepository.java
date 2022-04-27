package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Boolean existsByShield (String shield);
}
