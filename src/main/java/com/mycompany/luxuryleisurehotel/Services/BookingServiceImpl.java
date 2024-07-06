package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.DAO.BookingDao;
import com.mycompany.luxuryleisurehotel.DAOIMPL.BookingDaoImpl;
import com.mycompany.luxuryleisurehotel.Models.Booking;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingServiceImpl implements BookingService {

    private static final Logger logger = Logger.getLogger(BookingServiceImpl.class.getName());
    private BookingDao bookingDao;

    public BookingServiceImpl() {

        this.bookingDao = new BookingDaoImpl();
    }

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public boolean bookRoom(int userId, int roomId, Timestamp startTime, Timestamp endTime, String status, String diningPreference, double totalAmount) {
        if (userId <= 0 || roomId <= 0 || startTime == null || endTime == null || status == null || diningPreference == null) {
            logger.log(Level.WARNING, "Invalid input parameters for booking room");
            return false;
        }

        try {
            if (!isRoomAvailable(roomId, startTime, endTime)) {
                logger.log(Level.INFO, "Room {0} is not available for the given time period", roomId);
                return false;
            }

            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setRoomId(roomId);
            booking.setStartTime(startTime);
            booking.setEndTime(endTime);
            booking.setStatus(status);
            booking.setDiningPreference(diningPreference);
            booking.setTotalAmount(totalAmount);

            boolean success = bookingDao.addBooking(booking);
            if (success) {
                logger.log(Level.INFO, "Successfully booked room {0} for user {1}", new Object[]{roomId, userId});
            } else {
                logger.log(Level.WARNING, "Failed to book room {0} for user {1}", new Object[]{roomId, userId});
            }

            return success;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while booking room", e);
            return false;
        }
    }

    private boolean isRoomAvailable(int roomId, Timestamp startTime, Timestamp endTime) {
        List<Booking> bookings = bookingDao.getBookingsForRoom(roomId);

        for (Booking booking : bookings) {
            Timestamp existingStartTime = booking.getStartTime();
            Timestamp existingEndTime = booking.getEndTime();

            if (startTime.before(existingEndTime) && endTime.after(existingStartTime)) {
                logger.log(Level.INFO, "Room {0} is already booked from {1} to {2}", new Object[]{roomId, existingStartTime, existingEndTime});
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Booking> getAllBookings() {
        try {
            return bookingDao.getAllBookings();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all bookings", e);
            return null;
        }
    }

    @Override
    public List<Integer> getOccupiedRooms() {
        try {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            return bookingDao.getOccupiedRooms(currentTimestamp);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving occupied rooms", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Booking> getUserBookings(int userId) {
        try {
            return bookingDao.getUserBookings(userId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving user bookings", e);
            return null;
        }
    }

    @Override
    public Booking getBookingWithRoomDetails(int bookingId) {
        return bookingDao.getBookingWithRoomDetails(bookingId);
    }

    @Override
    public boolean deleteBooking(int bookingId) {
        return bookingDao.deleteBooking(bookingId);
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        try {
            return bookingDao.deleteBooking(bookingId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while cancelling booking", e);
            return false;
        }
    }

    @Override
    public List<Booking> getUserBookingHistory(int userId) {
        try {
            return bookingDao.getUserBookingHistory(userId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving user history bookings", e);
            return null;
        }
    }

    @Override
    public boolean addBooking(Booking booking) {
        return bookingDao.addBooking(booking);
    }

    @Override
    public boolean updateBooking(Booking booking) {
        return bookingDao.updateBooking(booking);
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingDao.getBookingById(id);
    }
}
