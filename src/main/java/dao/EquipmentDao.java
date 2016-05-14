/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Equipment;
import entity.Room;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ahas36
 */
public interface EquipmentDao extends Dao<Equipment, Integer>{
    List<Equipment> getAllAvialableEquipments(Date start,Date End);
    List<Equipment> getAllEquipmentsByType(String Type);
}
