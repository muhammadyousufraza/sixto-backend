package com.example.practice.project.service;

import com.example.practice.project.dto.PaymentDto;

public interface IPaymentService {

    PaymentDto getById(Long id);

    PaymentDto add(PaymentDto packageDto);

    PaymentDto update(PaymentDto paymentDto);

}
