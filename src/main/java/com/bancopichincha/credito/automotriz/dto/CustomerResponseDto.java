package com.bancopichincha.credito.automotriz.dto;

import com.bancopichincha.credito.automotriz.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Long id;
    private String fullName;


    private String address;
    private String phone;


    public CustomerResponseDto(Customer customer)
    {
        this.id=customer.getId();
        this.fullName =customer.getFirstName().concat(" ").concat(customer.getLastName());
        this.address= customer.getAddress();
        this.phone=customer.getPhone();

    }
    public Customer toCustomerEntity ()
    {
        Customer customerEntity = new Customer();
        customerEntity.setId(getId());
        return customerEntity;
    }
}
