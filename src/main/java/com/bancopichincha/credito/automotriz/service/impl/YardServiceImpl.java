package com.bancopichincha.credito.automotriz.service.impl;


import com.bancopichincha.credito.automotriz.domain.Yard;
import com.bancopichincha.credito.automotriz.dto.YardDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.helper.CSVHelper;
import com.bancopichincha.credito.automotriz.repository.CustomerYardRepository;
import com.bancopichincha.credito.automotriz.repository.YardRepository;
import com.bancopichincha.credito.automotriz.service.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    @Transactional(readOnly = true)
    public List<YardDto> getAllYard() {
        return yardRepository.findAll()
                .stream()
                .map(yard -> new YardDto(yard))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<YardDto> findByYardId(Long yardId) {
        return yardRepository.findById(yardId).map(YardDto::new);
    }

    @Override
    @Transactional
    public Optional<YardDto> addYard(YardDto yardDto) throws BadRequestException {
        Yard savedYard= yardRepository.save(yardDto.toYard());
        return Optional.ofNullable(savedYard).map(YardDto::new);
    }

    @Override
    @Transactional
    public Optional<YardDto> updateYard(Long yardId, YardDto dto) throws NotFoundException {
        Optional <Yard> yardFind = yardRepository.findById(yardId);
        return yardFind
                .map(
                        y -> { y = dto.toYard();
                            return yardRepository.save(y);})
                .map(YardDto::new);
    }

    @Override
    @Transactional
    public void deleteYard(Long yardId) throws NotFoundException, DataAssociateException {

        if (!yardRepository.existsById(yardId))
            throw new NotFoundException();

        //Buscar en la entidad YardCustomer para verificar relaciones
        if(customerYardRepository.existsById(yardId))
            throw new DataAssociateException("Patio con información asociada");

        yardRepository.deleteById(yardId);


    }

    @Override
    public void loadData(File file) throws DataDuplicateException, BadRequestException {
        //validate format
        if (!CSVHelper.isCSVFile(file))
            throw new BadRequestException("Formato incorrecto");

        try {
            List<Yard> yardList = CSVHelper.parseYardCvsFile(new FileInputStream(file));
            //validate duplicados
            Set<Yard> duplicates = CSVHelper.findDuplicateBySetAdd(yardList);
            if (!duplicates.isEmpty())
                throw new DataDuplicateException("datos duplicados ");

            yardRepository.saveAll(yardList);
        }
        catch (IOException ioException)
        {
            throw new RuntimeException("error con datos cvs: " + ioException.getMessage());
        }
    }
}
