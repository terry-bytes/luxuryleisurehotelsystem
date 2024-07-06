/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.DAO.PaymentDao;
import com.mycompany.luxuryleisurehotel.DAOIMPL.PaymentDaoImpl;
import com.mycompany.luxuryleisurehotel.Models.Payment;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Terry
 */
public class PaymentServiceImpl implements PaymentService {

    private PaymentDao paymentDao;

    public PaymentServiceImpl() {
        this.paymentDao = new PaymentDaoImpl();
    }

    @Override
    public boolean recordPayment(int bookingId, double amount, String paymentStatus) {
        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setPaymentTime(new Timestamp(System.currentTimeMillis()));
        payment.setPaymentStatus(paymentStatus);

        return paymentDao.addPayment(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }

    @Override
    public boolean deletePayment(int id) {
        return paymentDao.deletePayment(id);
    }
}
