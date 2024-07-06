/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.DAO;

import com.mycompany.luxuryleisurehotel.Models.Room;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Train
 */
public interface RoomDao {

    Room getRoomById(int roomId);

    List<Room> getAllRooms();

    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(int roomId);

    boolean isRoomAvailable(int roomId, Timestamp startTime, Timestamp endTime);

    List<Room> getAllAvailableRooms();
}
