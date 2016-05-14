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
public class JpaRoomDao extends JpaDao<Room, Integer> implements RoomDao{

    public JpaRoomDao() {
        super(Room.class);
    }

    @Override
    public List<Room> getAllAvialableRooms(Date start, Date end) {
        return entityManager.createQuery("Select nr from Room as nr where nr not in (SELECT r FROM Room AS r  INNER JOIN SubBooking AS sb on r.facility =sb.facilityId WHERE sb.subBookingSatuts='active' and ((sb.endDate BETWEEN :start and :end) or (sb.startDate BETWEEN :start and :end) or (sb.startDate<=:start and sb.endDate>=:end)))")
                .setParameter("start",start).setParameter("end", end) .getResultList();   
    }

    @Override
    public List<Room> getAllRomeByType(String type) {
        return entityManager.createQuery("SELECT m FROM Room AS m WHERE m.roomType=:rt").setParameter("rt", type).getResultList();
    }
    
}
