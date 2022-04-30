package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.CivilStatus;
import com.bancopichincha.credito.automotriz.domain.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto extends PersonDto {

    private Long id;

    @NotNull (message = "fecha nacimiento requerida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CsvBindByPosition(position = 6)
    @CsvDate(value = "dd-MM-yyyy")
    private Date birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @CsvBindByPosition(position = 7)
    private CivilStatus civilStatus;


    @NotNull
    @CsvBindByPosition(position = 8)
    private String documentNumberWife;

    @NotNull
    @CsvBindByPosition(position = 9)
    private String fullNameWife;

    @NotNull
    @CsvBindByPosition(position = 10)
    private Boolean verifyCredit = Boolean.FALSE;

    public CustomerDto(Customer customer) {
        super (customer.getId(),customer.getDocumentNumber(),customer.getFirstName(), customer.getLastName(), customer.getAge(),
                customer.getAddress(), customer.getPhone());
        this.id= customer.getId();
        this.civilStatus = customer.getCivilStatus();
        this.documentNumberWife=customer.getDocumentNumberWife();
        this.fullNameWife = customer.getFullNameWife();
    }

    public Customer toCustomer()
    {
        Customer customer = new Customer();
        customer.setId(getId());
        customer.setFirstName(this.getFirstName());
        customer.setLastName(this.getLastName());
        customer.setDocumentNumber(this.getDocumentNumber());
        customer.setBirthDate(this.getBirthDate());
        customer.setAge(this.getAge());
        customer.setAddress(this.getAddress());
        customer.setPhone(this.getPhone());
        customer.setCivilStatus(this.getCivilStatus());
        customer.setDocumentNumberWife(this.getDocumentNumberWife());
        customer.setFullNameWife(this.getFullNameWife());
        return  customer;
    }

}
