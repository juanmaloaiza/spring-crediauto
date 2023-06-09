package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.dto.YardDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface YardService {

    List<YardDto> getAllYard();

    Optional<YardDto> findByYardId (Long yardId);

    Optional<YardDto> addYard (YardDto yardDto) throws BadRequestException;

    Optional <YardDto> updateYard (Long yardId, YardDto dto) throws NotFoundException;

    void deleteYard (Long yardId) throws NotFoundException, DataAssociateException;

    void loadData (File file) throws DataDuplicateException, BadRequestException;
}
