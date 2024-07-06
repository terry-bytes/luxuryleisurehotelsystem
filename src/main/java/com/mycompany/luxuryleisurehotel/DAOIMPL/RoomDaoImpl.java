package com.mycompany.luxuryleisurehotel.DAOIMPL;

import com.mycompany.luxuryleisurehotel.DAO.RoomDao;
import com.mycompany.luxuryleisurehotel.Models.Room;
import com.mycompany.luxuryleisurehotel.Util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private final Connection connection;

    public RoomDaoImpl() {
        this.connection = DbUtil.getConnection();
    }

    @Override
    public Room getRoomById(int roomId) {
        String query = "SELECT * FROM rooms WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getInt("id"),
                            rs.getString("room_type"),
                            rs.getDouble("rate"),
                            rs.getInt("max_occupancy")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomType(rs.getString("room_type"));
                room.setRate(rs.getDouble("rate"));
                room.setMaxOccupancy(rs.getInt("max_occupancy"));
                rooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rooms;
    }

    @Override
    public boolean addRoom(Room room) {
        String query = "INSERT INTO rooms (room_type, rate, max_occupancy) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, room.getRoomType());
            pstmt.setDouble(2, room.getRate());
            pstmt.setInt(3, room.getMaxOccupancy());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRoom(Room room) {
        String query = "UPDATE rooms SET room_type = ?, rate = ?, max_occupancy = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, room.getRoomType());
            pstmt.setDouble(2, room.getRate());
            pstmt.setInt(3, room.getMaxOccupancy());
            pstmt.setInt(4, room.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room updated successfully.");
                return true;
            } else {
                System.out.println("No room was updated. Room ID might be incorrect.");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("SQL error while updating room: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRoom(int roomId) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isRoomAvailable(int roomId, Timestamp startTime, Timestamp endTime) {
        String sql = "SELECT COUNT(*) FROM bookings WHERE room_id = ? AND ((start_time <= ? AND end_time >= ?) OR (start_time >= ? AND start_time < ?) OR (end_time > ? AND end_time <= ?))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomId);
            statement.setTimestamp(2, startTime);
            statement.setTimestamp(3, startTime);
            statement.setTimestamp(4, startTime);
            statement.setTimestamp(5, endTime);
            statement.setTimestamp(6, startTime);
            statement.setTimestamp(7, endTime);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Room> getAllAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        String query = "SELECT r.* " +
                       "FROM rooms r " +
                       "LEFT JOIN bookings b ON r.id = b.room_id " +
                       "AND (b.start_time <= NOW() AND b.end_time >= NOW()) " +
                       "WHERE b.room_id IS NULL";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomType(rs.getString("room_type"));
                room.setRate(rs.getDouble("rate"));
                room.setMaxOccupancy(rs.getInt("max_occupancy"));
                availableRooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return availableRooms;
    }
}
