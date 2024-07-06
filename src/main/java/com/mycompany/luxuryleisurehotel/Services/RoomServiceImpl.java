/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.DAO.RoomDao;
import com.mycompany.luxuryleisurehotel.DAOIMPL.RoomDaoImpl;
import com.mycompany.luxuryleisurehotel.Models.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Train
 */
public class RoomServiceImpl implements RoomService {

    private Connection con;

    private final RoomDao roomDao;

    public RoomServiceImpl() {
        this.roomDao = new RoomDaoImpl();
    }

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.getAllRooms();
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomDao.getRoomById(roomId);
    }

    @Override
    public boolean addRoom(Room room) {
        return roomDao.addRoom(room);
    }

    @Override
    public boolean updateRoom(Room room) {
        return roomDao.updateRoom(room);
    }

    @Override
    public boolean deleteRoom(int roomId) {
        return roomDao.deleteRoom(roomId);
    }

    @Override
    public boolean isRoomAvailable(int roomId, Timestamp startTime, Timestamp endTime) {
        return roomDao.isRoomAvailable(roomId, startTime, endTime);
    }

    @Override
    public List<Room> getAllAvailableRooms() {
        return roomDao.getAllAvailableRooms();
    }

}
