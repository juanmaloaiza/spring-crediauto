package com.bancopichincha.credito.automotriz.controller;


import com.bancopichincha.credito.automotriz.dto.CreditDto;
import com.bancopichincha.credito.automotriz.exception.*;
import com.bancopichincha.credito.automotriz.service.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CreditController.CREDIT)
@Slf4j
public class CreditController {

    public static final String CREDIT="/solicitudCredito";

    private CreditService creditService;


    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public ResponseEntity<List<CreditDto>> findAll ()
    {
        log.debug("busqueda de clientes Endpoint GET: /api/solicitudCredito");
        return new ResponseEntity<>(this.creditService.getAllCustomers(), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody @Valid CreditDto creditDto) {
        try {
            creditDto = creditService.addCredit(creditDto);
            return new ResponseEntity<>(creditDto, HttpStatus.CREATED);
        } catch (DataDuplicateException | NotFoundException | BadRequestException | DataAssociateException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return new ResponseEntity(errorMessage,HttpStatus.ACCEPTED);
        }
    }
}
