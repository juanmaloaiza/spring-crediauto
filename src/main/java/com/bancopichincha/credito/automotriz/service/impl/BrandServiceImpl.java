package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.domain.Brand;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.helper.CSVHelper;
import com.bancopichincha.credito.automotriz.repository.BrandRepository;
import com.bancopichincha.credito.automotriz.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void loadData(File file) throws DataDuplicateException, BadRequestException {

        //validate format
        if (!CSVHelper.isCSVFile(file))
            throw new BadRequestException("Formato incorrecto");

        try {
            List<Brand> brandList = CSVHelper.parseCsvFile(new FileInputStream(file));
            //validate duplicados
            Set<Brand> duplicates = CSVHelper.findDuplicateBySetAdd(brandList);
            if (!duplicates.isEmpty())
                throw new DataDuplicateException("datos duplicados ");

            brandRepository.saveAll(brandList);
        }
        catch (IOException ioException)
        {
            throw new RuntimeException("error con datos cvs: " + ioException.getMessage());
        }

    }
}
