package org.example.cloud.payment.service.impl;

import org.example.cloud.payment.dao.PaymentDao;
import org.example.cloud.payment.entity.Payment;
import org.example.cloud.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuchen
 * Aug 20, 2021
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentDao.getById(id);
    }
}
