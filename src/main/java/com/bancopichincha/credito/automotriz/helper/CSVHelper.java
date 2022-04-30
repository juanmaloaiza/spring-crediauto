package com.bancopichincha.credito.automotriz.helper;

import com.bancopichincha.credito.automotriz.domain.Brand;
import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.domain.Executive;
import com.bancopichincha.credito.automotriz.domain.Yard;
import com.bancopichincha.credito.automotriz.dto.BrandDto;
import com.bancopichincha.credito.automotriz.dto.CustomerDto;
import com.bancopichincha.credito.automotriz.dto.ExecutiveDto;
import com.bancopichincha.credito.automotriz.dto.YardDto;
import com.bancopichincha.credito.automotriz.service.YardService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVHelper {

    public static String TYPE ="csv";

    private  YardService yardService;


    public static  boolean isCSVFile (File file){
        String extension = file.getName().split("\\.")[1];
        if (!extension.equals(TYPE))
            return false;
        return true;
    }
    public static List<Brand> parseCsvFile (InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             ) {
            List<Brand> brands = new ArrayList<>();

            CsvToBean csvToBean = new CsvToBeanBuilder(fileReader)
                    .withType(BrandDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (BrandDto item : (Iterable<BrandDto>) csvToBean) {
                brands.add(item.toBrand());
            }

           return brands;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public  static List<ExecutiveDto> parseExecutiveCvsFile (InputStream inputStream)
    {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        ) {
            List<ExecutiveDto> executives = new ArrayList<>();

            /*CsvToBean csvToBean = new CsvToBeanBuilder(fileReader)
                    .withType(ExecutiveDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();*/

            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();


            for (CSVRecord csvRecord : csvRecords) {

                ExecutiveDto executiveDto = new ExecutiveDto();
                executiveDto.setDocumentNumber(String.valueOf(csvRecord.get(0)));
                executiveDto.setFirstName(String.valueOf(csvRecord.get(1)));
                executiveDto.setLastName(String.valueOf(csvRecord.get(2)));
                executiveDto.setAge(Integer.valueOf(csvRecord.get(3)));
                executiveDto.setAddress(String.valueOf(csvRecord.get(4)));
                executiveDto.setPhone(String.valueOf(csvRecord.get(5)));
                executiveDto.setCellPhone(String.valueOf(csvRecord.get(7)));
                Integer idYard =Integer.valueOf(csvRecord.get(6));
                executiveDto.setIdYard(idYard.longValue());
                executives.add(executiveDto);
            }

            /*for (ExecutiveDto item : (Iterable<ExecutiveDto>) csvToBean) {
                item.setYardDto(yardService.findByYardId(1L).get());
                executives.add(item.toExecutive());
            }*/

            return executives;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
    public static List<Customer> parseCustomerCvsFile (InputStream inputStream)
    {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        ) {
            List<Customer> customerList = new ArrayList<>();

            CsvToBean csvToBean = new CsvToBeanBuilder(fileReader)
                    .withType(CustomerDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (CustomerDto item : (Iterable<CustomerDto>) csvToBean) {
                customerList.add(item.toCustomer());
            }

            return customerList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Yard> parseYardCvsFile (InputStream inputStream)
    {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        ) {
            List<Yard> yardList = new ArrayList<>();

            CsvToBean csvToBean = new CsvToBeanBuilder(fileReader)
                    .withType(YardDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (YardDto item : (Iterable<YardDto>) csvToBean) {
                yardList.add(item.toYard());
            }
            return yardList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static <T> Set<T> findDuplicateBySetAdd(List<T> list) {

        Set<T> items = new HashSet<>();
        return list.stream()
                .filter(n -> !items.add(n))
                .collect(Collectors.toSet());
    }




}
