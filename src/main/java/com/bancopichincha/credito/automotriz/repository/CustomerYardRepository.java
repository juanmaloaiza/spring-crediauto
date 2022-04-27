package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.domain.CustomerYard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerYardRepository extends JpaRepository<CustomerYard,Long> {

    Boolean existsByCustomerId (Long idCustomer);

    Boolean existsByYardId (Long idYard);
}
