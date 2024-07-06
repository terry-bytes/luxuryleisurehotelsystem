/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.Models.Booking;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    boolean bookRoom(int userId, int roomId, Timestamp startTime, Timestamp endTime, String status, String diningPreference, double totalAmount);

    List<Booking> getAllBookings();

    List<Integer> getOccupiedRooms();

    List<Booking> getUserBookings(int userId);

    Booking getBookingWithRoomDetails(int bookingId);

    boolean deleteBooking(int bookingId);

    boolean cancelBooking(int bookingId);

    List<Booking> getUserBookingHistory(int userId);

    boolean addBooking(Booking booking);

    boolean updateBooking(Booking booking);

    Booking getBookingById(int id);

}
