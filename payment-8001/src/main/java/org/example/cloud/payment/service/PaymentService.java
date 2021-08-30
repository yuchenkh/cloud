package org.example.cloud.payment.service;

import org.apache.ibatis.annotations.Param;
import org.example.cloud.payment.entity.Payment;

/**
 * @author yuchen
 * Aug 20, 2021
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getById(@Param("id") Long id);
}
