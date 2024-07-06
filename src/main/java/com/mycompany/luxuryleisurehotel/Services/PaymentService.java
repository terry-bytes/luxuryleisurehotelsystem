/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.Models.Payment;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface PaymentService {

    boolean recordPayment(int bookingId, double amount, String paymentStatus);

    List<Payment> getAllPayments();

    boolean deletePayment(int id);
}
