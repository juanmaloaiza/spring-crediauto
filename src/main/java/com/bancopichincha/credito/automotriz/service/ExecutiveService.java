package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import java.io.File;


public interface ExecutiveService {

    void loadData (File file) throws DataDuplicateException, BadRequestException;

}
