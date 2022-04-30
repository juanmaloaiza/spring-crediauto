package com.bancopichincha.credito.automotriz.dto;


import com.bancopichincha.credito.automotriz.domain.Customer;
import com.bancopichincha.credito.automotriz.domain.CustomerYard;
import com.bancopichincha.credito.automotriz.domain.Yard;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerYardDto {

    private Long id;
    @NotNull (message = "cliente requerido")
    private CustomerDto customer;

    @NotNull (message = "Patio requerido")
    private YardDto yard;


    @NotNull (message = "fecha asignacion requerida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateAssign;

    public CustomerYardDto (CustomerYard customerYard)
    {
        this.customer = new CustomerDto(customerYard.getCustomer());
        this.yard = new YardDto(customerYard.getYard());
        this.dateAssign = LocalDate.now();
    }

    public CustomerYard toCustomerYard ()
    {
        CustomerYard customerYard = new CustomerYard();
        customerYard.setCustomer(this.customer.toCustomer());
        customerYard.setYard(this.yard.toYard());
        customerYard.setDateAssign(this.dateAssign);
        return  customerYard;
    }

}
