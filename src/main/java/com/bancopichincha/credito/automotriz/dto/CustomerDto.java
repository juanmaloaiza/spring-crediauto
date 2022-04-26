package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.CivilStatus;
import com.bancopichincha.credito.automotriz.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CustomerDto {


    @NotBlank
    private String documentNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDate birthDate;


    private Integer age;


    @NotBlank
    private String address;


    @NotBlank
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CivilStatus civilStatus;


    @NotBlank
    private String documentNumberWife;

    @NotBlank
    private String fullNameWife;


    public CustomerDto (Customer customer)
    {
        this.firstName =customer.getFirstName();
        this.lastName = customer.getLastName();
        this.documentNumber=customer.getDocumentNumber();
        this.birthDate = customer.getBirthDate();
        this.age= 10;
        this.address= customer.getAddress();
        this.phone=customer.getPhone();
        this.civilStatus = customer.getCivilStatus();
        this.documentNumberWife=customer.getDocumentNumberWife();
        this.fullNameWife = customer.getFullNameWife();
    }

    public Customer toCustomer()
    {
        Customer customer = new Customer();
        customer.setFirstName(this.getFirstName());
        customer.setLastName(this.getLastName());
        customer.setDocumentNumber(this.getDocumentNumber());
        customer.setBirthDate(this.getBirthDate());
        customer.setAddress(this.getAddress());
        customer.setPhone(this.getPhone());
        customer.setCivilStatus(this.getCivilStatus());
        customer.setDocumentNumberWife(this.getDocumentNumberWife());
        customer.setFullNameWife(this.getFullNameWife());
        return  customer;
    }



}
