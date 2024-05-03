package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.PAYMENT;

import com.example.practice.project.dto.PaymentDto;
import com.example.practice.project.service.IPaymentService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping(API + PAYMENT)
public class PaymentController {

    @Autowired
    IPaymentService paymentService;

    @PostMapping()
    public ResponseEntity<PaymentDto> save(@Valid @RequestBody PaymentDto paymentDto) {
        log.info("Payment save Request: {}", paymentDto);
        PaymentDto addedPayment = paymentService.add(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPayment);
    }

    @PutMapping()
    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto paymentDto) {
        log.info("Update Payment By Id Request: {}", paymentDto.getId());
        PaymentDto packageDto = paymentService.update(paymentDto);
        return ResponseEntity.ok(packageDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> get(@PathVariable Long id) {
        log.debug("Get Payment By Id Request: {}", id);
        PaymentDto paymentDto = paymentService.getById(id);
        return ResponseEntity.ok(paymentDto);
    }

}
