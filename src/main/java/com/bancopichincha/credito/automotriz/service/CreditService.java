package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.dto.CreditDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;


import java.util.List;
import java.util.Optional;

public interface CreditService {

    List<CreditDto> getAllCustomers();

    Optional<CreditDto> findById (Long creditId);

    CreditDto addCredit(CreditDto creditDto) throws BadRequestException, DataAssociateException, DataDuplicateException, NotFoundException;

}
