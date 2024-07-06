package com.mycompany.luxuryleisurehotel.DAOIMPL;

import com.mycompany.luxuryleisurehotel.DAO.BookingDao;
import com.mycompany.luxuryleisurehotel.Models.Booking;
import com.mycompany.luxuryleisurehotel.Models.Room;
import com.mycompany.luxuryleisurehotel.Util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDaoImpl implements BookingDao {

    private final Connection connection;

    public BookingDaoImpl() {
        this.connection = DbUtil.getConnection();
    }

    @Override
    public boolean bookRoom(int userId, int roomId, Timestamp startTime, Timestamp endTime, String status, String diningPreference) {
        String query = "INSERT INTO bookings (user_id, room_id, start_time, end_time, status, dining_preference) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, roomId);
            pstmt.setTimestamp(3, startTime);
            pstmt.setTimestamp(4, endTime);
            pstmt.setString(5, status);
            pstmt.setString(6, diningPreference);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                bookings.add(mapBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean addBooking(Booking booking) {
        String query = "INSERT INTO bookings (user_id, room_id, start_time, end_time, status, dining_preference) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getRoomId());
            pstmt.setTimestamp(3, booking.getStartTime());
            pstmt.setTimestamp(4, booking.getEndTime());
            pstmt.setString(5, booking.getStatus());
            pstmt.setString(6, booking.getDiningPreference());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Booking> getBookingsForRoom(int roomId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE room_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapBooking(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> getUserBookings(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ? AND end_time > CURRENT_TIMESTAMP";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapBooking(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Integer> getOccupiedRooms(Timestamp currentTimestamp) {
        List<Integer> occupiedRooms = new ArrayList<>();
        String query = "SELECT room_id FROM bookings WHERE start_time <= ? AND end_time >= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setTimestamp(1, currentTimestamp);
            pstmt.setTimestamp(2, currentTimestamp);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    occupiedRooms.add(rs.getInt("room_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return occupiedRooms;
    }

    @Override
    public Booking getBookingWithRoomDetails(int bookingId) {
        String query = "SELECT b.*, r.id AS room_id, r.room_type, r.rate, r.max_occupancy "
                + "FROM bookings b "
                + "JOIN rooms r ON b.room_id = r.id "
                + "WHERE b.id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, bookingId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Booking booking = mapBooking(rs);
                    Room room = new Room();
                    room.setId(rs.getInt("room_id"));
                    room.setRoomType(rs.getString("room_type"));
                    room.setRate(rs.getDouble("rate"));
                    room.setMaxOccupancy(rs.getInt("max_occupancy"));
                    booking.setRoom(room);
                    return booking;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteBooking(int id) {
        String query = "DELETE FROM bookings WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Booking mapBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("id"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setRoomId(rs.getInt("room_id"));
        booking.setStartTime(rs.getTimestamp("start_time"));
        booking.setEndTime(rs.getTimestamp("end_time"));
        booking.setStatus(rs.getString("status"));
        booking.setDiningPreference(rs.getString("dining_preference"));
        return booking;
    }

    @Override
    public List<Booking> getUserBookingHistory(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ? AND end_time < CURRENT_TIMESTAMP";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapBooking(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean updateBooking(Booking booking) {
        String query = "UPDATE bookings SET user_id = ?, room_id = ?, start_time = ?, end_time = ?, status = ?, dining_preference = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getRoomId());
            pstmt.setTimestamp(3, booking.getStartTime());
            pstmt.setTimestamp(4, booking.getEndTime());
            pstmt.setString(5, booking.getStatus());
            pstmt.setString(6, booking.getDiningPreference());
            pstmt.setInt(7, booking.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Booking getBookingById(int id) {
        Booking booking = null;
        String query = "SELECT * FROM bookings WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    booking = new Booking();
                    booking.setId(rs.getInt("id"));
                    booking.setUserId(rs.getInt("user_id"));
                    booking.setRoomId(rs.getInt("room_id"));
                    booking.setStartTime(rs.getTimestamp("start_time"));
                    booking.setEndTime(rs.getTimestamp("end_time"));
                    booking.setStatus(rs.getString("status"));
                    booking.setDiningPreference(rs.getString("dining_preference"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booking;
    }

}
