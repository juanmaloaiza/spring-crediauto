package com.bancopichincha.credito.automotriz.service;


import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface BrandService {

    void loadData (File file) throws DataDuplicateException, BadRequestException;

}
