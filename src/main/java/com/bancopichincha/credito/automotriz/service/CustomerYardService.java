package com.bancopichincha.credito.automotriz.service;


import com.bancopichincha.credito.automotriz.dto.CustomerYardDto;
import com.bancopichincha.credito.automotriz.exception.*;


import java.util.Optional;

public interface CustomerYardService {


    Optional<CustomerYardDto> add (CustomerYardDto customer) throws DataDuplicateException, BadRequestException;

    Optional<CustomerYardDto> update (Long customerId, CustomerYardDto customer) throws NotFoundException,
            DataDuplicateException;

    void delete (Long customerId) throws  NotFoundException;


}
