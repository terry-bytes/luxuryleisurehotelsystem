/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.Models.Room;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Train
 */
public interface RoomService {

    boolean isRoomAvailable(int roomId, Timestamp startTime, Timestamp endTime);

    List<Room> getAllRooms();

    Room getRoomById(int roomId);

    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(int roomId);

    List<Room> getAllAvailableRooms();
}
