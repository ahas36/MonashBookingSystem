/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Room;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ahas36
 */
public interface RoomDao extends Dao<Room, Integer>{
    List<Room> getAllAvialableRooms(Date start,Date End);
    List<Room> getAllRomeByType(String type);
}
