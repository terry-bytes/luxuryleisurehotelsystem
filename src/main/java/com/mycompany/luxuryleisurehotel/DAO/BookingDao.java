package com.mycompany.luxuryleisurehotel.DAO;

import com.mycompany.luxuryleisurehotel.Models.Booking;
import java.sql.Timestamp;
import java.util.List;

public interface BookingDao {

    boolean bookRoom(int userId, int roomId, Timestamp startTime, Timestamp endTime, String status, String diningPreference);

    List<Booking> getAllBookings();

    boolean addBooking(Booking booking);

    List<Booking> getBookingsForRoom(int roomId);

    public List<Booking> getUserBookings(int userId);

    public List<Integer> getOccupiedRooms(Timestamp currentTimestamp);

    Booking getBookingWithRoomDetails(int bookingId);

    public boolean deleteBooking(int id);

    public List<Booking> getUserBookingHistory(int userId);

    boolean updateBooking(Booking booking);

    Booking getBookingById(int id);
}
