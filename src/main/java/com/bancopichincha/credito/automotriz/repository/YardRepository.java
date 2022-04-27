package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.domain.Yard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YardRepository extends JpaRepository<Yard,Long> {

}
