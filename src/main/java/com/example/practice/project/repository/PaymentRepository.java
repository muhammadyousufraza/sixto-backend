package com.example.practice.project.repository;

import com.example.practice.project.entity.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CustomRepository<Payment, Long> {

}
