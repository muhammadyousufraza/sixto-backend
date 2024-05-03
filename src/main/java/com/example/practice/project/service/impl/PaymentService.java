package com.example.practice.project.service.impl;

import static com.example.practice.project.utilities.Constants.PAYMENT_NOT_FOUND;

import com.example.practice.project.customexception.NotFoundException;
import com.example.practice.project.dto.PaymentDto;
import com.example.practice.project.entity.Payment;
import com.example.practice.project.repository.PaymentRepository;
import com.example.practice.project.service.IPaymentService;
import com.example.practice.project.utilities.ModelConverter;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentDto getById(Long id) {
        log.info("Getting payment by id: {}", id);
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            return ModelConverter.convertToDto(optionalPayment.get());
        } else {
            log.error(PAYMENT_NOT_FOUND + " with parameter : {}", id);
            throw new NotFoundException(PAYMENT_NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public PaymentDto add(PaymentDto paymentDto) {
        log.info("Adding a new Payment..");

        Payment payment = ModelConverter.convertToEntity(paymentDto);

        payment.setCreatedDate(LocalDateTime.now());
        payment.setUpdatedDate(LocalDateTime.now());

        payment = paymentRepository.save(payment);

        paymentDto.setId(payment.getId());
        paymentDto.setCompanyId(payment.getCompany().getId());

        return paymentDto;
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        log.info("Updating payment with ID: {}", paymentDto.getId());
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentDto.getId());

        if (!optionalPayment.isPresent()) {
            log.error(PAYMENT_NOT_FOUND + " while updating with parameter : {}", paymentDto.getId());
            throw new NotFoundException(PAYMENT_NOT_FOUND);
        }

        Payment savedPayment = ModelConverter.convertToEntity(paymentDto);

        savedPayment.setCreatedDate(optionalPayment.get().getCreatedDate());
        savedPayment.setUpdatedDate(LocalDateTime.now());

        savedPayment = paymentRepository.save(savedPayment);
        PaymentDto updatedPackageDto = ModelConverter.convertToDto(savedPayment);

        return updatedPackageDto;
    }


}
