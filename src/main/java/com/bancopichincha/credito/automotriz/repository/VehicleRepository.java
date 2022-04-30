package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.domain.Brand;
import com.bancopichincha.credito.automotriz.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Boolean existsByShield (String shield);

    @Query("select v from Vehicle v where v.brand.name =?1 or v.model=?2 or v.year=?3")
    List<Vehicle> findByBrandOrModelOrYear(String brandName, String model, Integer year);
}
