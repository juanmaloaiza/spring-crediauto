package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.domain.Executive;
import com.bancopichincha.credito.automotriz.domain.Yard;
import com.bancopichincha.credito.automotriz.dto.ExecutiveDto;
import com.bancopichincha.credito.automotriz.dto.YardDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.helper.CSVHelper;
import com.bancopichincha.credito.automotriz.repository.ExecutiveRepository;
import com.bancopichincha.credito.automotriz.repository.YardRepository;
import com.bancopichincha.credito.automotriz.service.ExecutiveService;
import com.bancopichincha.credito.automotriz.service.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

    private ExecutiveRepository executiveRepository;

    private YardRepository yardRepository;

    @Autowired
    public ExecutiveServiceImpl(ExecutiveRepository executiveRepository, YardRepository yardRepository) {
        this.executiveRepository = executiveRepository;
        this.yardRepository = yardRepository;
    }

    @Override
    public void loadData(File file) throws DataDuplicateException, BadRequestException {
        //validate format
        if (!CSVHelper.isCSVFile(file))
            throw new BadRequestException("Formato incorrecto");

        try {
            List<Executive> executiveList = new ArrayList<>();
            List<ExecutiveDto> listDto = CSVHelper.parseExecutiveCvsFile(new FileInputStream(file));
            //validate duplicados
            for (ExecutiveDto item : listDto)
            {
                Executive executive = item.toExecutive();
                Yard yard = yardRepository.findById(item.getIdYard()).orElse(new Yard());
                executive.setYard(yard);
                executiveList.add(executive);
            }

                Set<Executive> duplicates = CSVHelper.findDuplicateBySetAdd(executiveList);
            if (!duplicates.isEmpty())
                throw new DataDuplicateException("datos duplicados ");

            executiveRepository.saveAll(executiveList);
        }
        catch (IOException ioException)
        {
            throw new RuntimeException("error con datos cvs: " + ioException.getMessage());
        }

    }
}
