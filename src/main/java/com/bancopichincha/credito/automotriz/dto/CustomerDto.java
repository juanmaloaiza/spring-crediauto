package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.CivilStatus;
import com.bancopichincha.credito.automotriz.domain.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto extends PersonDto {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CivilStatus civilStatus;


    @NotBlank
    private String documentNumberWife;

    @NotBlank
    private String fullNameWife;

    public CustomerDto(Customer customer) {
        super (customer.getDocumentNumber(),customer.getFirstName(), customer.getLastName(), customer.getAge(),
                customer.getAddress(), customer.getPhone());
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
        customer.setAge(this.getAge());
        customer.setAddress(this.getAddress());
        customer.setPhone(this.getPhone());
        customer.setCivilStatus(this.getCivilStatus());
        customer.setDocumentNumberWife(this.getDocumentNumberWife());
        customer.setFullNameWife(this.getFullNameWife());
        return  customer;
    }

}
