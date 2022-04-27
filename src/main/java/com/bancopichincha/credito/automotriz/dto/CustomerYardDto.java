package com.bancopichincha.credito.automotriz.dto;


import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.domain.CustomerYard;
import com.bancopichincha.credito.automotriz.domain.Yard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerYardDto {


    private Customer customer;

    private Yard yard;
    private LocalDate dateAssign;

    public CustomerYardDto (CustomerYard customerYard)
    {
        this.customer = customerYard.getCustomer();
        this.yard = customerYard.getYard();
        this.dateAssign = customerYard.getDateAssign();
    }

    public CustomerYard toCustomerYard ()
    {
        CustomerYard customerYard = new CustomerYard();
        customerYard.setCustomer(this.customer);
        customerYard.setYard(this.yard);
        customerYard.setDateAssign(this.dateAssign);
        return  customerYard;
    }

}
