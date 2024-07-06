/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.luxuryleisurehotel.DAOIMPL;

import com.mycompany.luxuryleisurehotel.DAO.PaymentDao;
import com.mycompany.luxuryleisurehotel.Models.Payment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Terry
 */
public class PaymentDaoImpl implements PaymentDao {

    @Override
    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payment (booking_id, amount, payment_time, payment_status) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel", "root", "root");
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, payment.getBookingId());
            ps.setDouble(2, payment.getAmount());
            ps.setTimestamp(3, payment.getPaymentTime());
            ps.setString(4, payment.getPaymentStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel", "root", "root");
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setBookingId(rs.getInt("booking_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentTime(rs.getTimestamp("payment_time"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                payments.add(payment);

          
                System.out.println("Fetched Payment: " + payment);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public boolean deletePayment(int id) {
        String sql = "DELETE FROM payment WHERE id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel", "root", "root");
     PreparedStatement ps = con.prepareStatement(sql)) {
    ps.setInt(1, id);
    int rowsDeleted = ps.executeUpdate();
    if (rowsDeleted > 0) {
        System.out.println("Payment deleted successfully.");
        return true;
    } else {
        System.out.println("No payment found with id: " + id);
        return false;
    }
} catch (SQLException e) {
    System.err.println("Error deleting payment with id: " + id);
    e.printStackTrace();
    return false;
}


}
}
