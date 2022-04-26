package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private String fullName;

    private String lastName;
    private String address;
    private String phone;


    public CustomerResponseDto(Customer customer)
    {
        this.fullName =customer.getFirstName().concat(" ").concat(customer.getLastName());
        this.address= customer.getAddress();
        this.phone=customer.getPhone();

    }
}
