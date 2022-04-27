package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Yard;
import com.bancopichincha.credito.automotriz.dto.YardDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.repository.CustomerYardRepository;
import com.bancopichincha.credito.automotriz.repository.YardRepository;
import com.bancopichincha.credito.automotriz.service.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YardServiceImpl implements YardService {

    private YardRepository yardRepository;
    private CustomerYardRepository customerYardRepository;

    @Autowired
    public YardServiceImpl(YardRepository yardRepository, CustomerYardRepository customerYardRepository) {
        this.yardRepository = yardRepository;
        this.customerYardRepository = customerYardRepository;
    }

    @Override
    public List<YardDto> getAllYard() {
        return yardRepository.findAll()
                .stream()
                .map(yard -> new YardDto(yard))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<YardDto> findByYardId(Long yardId) {
        return yardRepository.findById(yardId).map(YardDto::new);
    }

    @Override
    public Optional<YardDto> addYard(YardDto yardDto) throws BadRequestException {
        Yard savedYard= yardRepository.save(yardDto.toYard());
        return Optional.ofNullable(savedYard).map(YardDto::new);
    }

    @Override
    public Optional<YardDto> updateYard(Long yardId, YardDto dto) throws NotFoundException {
        Optional <Yard> yardFind = yardRepository.findById(yardId);

        return yardFind
                .map(y -> yardRepository.save(y))
                .map(YardDto::new);
    }

    @Override
    public void deleteYard(Long yardId) throws NotFoundException, DataAssociateException {

        if (!yardRepository.existsById(yardId))
            throw new NotFoundException();

        //Buscar en la entidad YardCustomer para verificar relaciones
        if(customerYardRepository.existsById(yardId))
            throw new DataAssociateException("Patio con información asociada");

        yardRepository.deleteById(yardId);


    }
}
