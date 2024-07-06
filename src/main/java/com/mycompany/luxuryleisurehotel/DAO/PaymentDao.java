/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.luxuryleisurehotel.DAO;

import com.mycompany.luxuryleisurehotel.Models.Payment;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface PaymentDao {

    boolean addPayment(Payment payment);

    List<Payment> getAllPayments();

    boolean deletePayment(int id);
}
