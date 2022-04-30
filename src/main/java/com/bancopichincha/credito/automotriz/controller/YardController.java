package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.dto.YardDto;
import com.bancopichincha.credito.automotriz.exception.BadRequestException;
import com.bancopichincha.credito.automotriz.exception.DataAssociateException;
import com.bancopichincha.credito.automotriz.exception.ErrorMessage;
import com.bancopichincha.credito.automotriz.exception.NotFoundException;
import com.bancopichincha.credito.automotriz.service.YardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(YardController.YARD)
@Slf4j
public class YardController {

    public static final String YARD="/patios";

    private final YardService yardService;

    public YardController(YardService yardService) {
        this.yardService = yardService;
    }

    @GetMapping
    public ResponseEntity<List<YardDto>> findAll()
    {
        log.info("Busqueda de patios Endpoint GET: /api/patios" );
        return new ResponseEntity<>(this.yardService.getAllYard(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <YardDto> save (@Validated @RequestBody YardDto yardDto) throws BadRequestException {

        log.info("crear un patio {}", yardDto );
            return yardService.addYard(yardDto)
                    .map(yard -> new ResponseEntity<>(yard,HttpStatus.CREATED))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("{yard-id}")
    public ResponseEntity<YardDto> update( @PathVariable(value="yard-id") Long yardId,
    @Validated @RequestBody YardDto yardDto) throws NotFoundException {
        log.info("actualizar un patio " +  yardId.toString() + " dto: " + yardDto);
        return yardService.updateYard(yardId,yardDto)
                .map(yard -> new ResponseEntity<>(yard, HttpStatus.OK))
                .orElse(new ResponseEntity(new ErrorMessage("No existe patio"),HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{yard-id}")
    public ResponseEntity<?> deleteById (@PathVariable (value = "yard-id") Long yardId) throws NotFoundException, DataAssociateException {

        log.info("eliminar un patio id:  {}", yardId );
        yardService.deleteYard(yardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
