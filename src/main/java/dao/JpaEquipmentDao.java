/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Equipment;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ahas36
 */
public class JpaEquipmentDao extends JpaDao<Equipment, Integer> implements EquipmentDao {

    public JpaEquipmentDao() {
        super(Equipment.class);
    }

    @Override
    public List<Equipment> getAllAvialableEquipments(Date start, Date end) {
        return entityManager.createQuery("Select nr from Equipment as nr where nr not in (SELECT r FROM Equipment AS r  INNER JOIN SubBooking AS sb on r.facility =sb.facilityId WHERE sb.subBookingSatuts='active' and ((sb.endDate BETWEEN :start and :end) or (sb.startDate BETWEEN :start and :end) or (sb.startDate<=:start and sb.endDate>=:end)))")
                .setParameter("start",start).setParameter("end", end) .getResultList();
    }

    @Override
    public List<Equipment> getAllEquipmentsByType(String type) {
        return entityManager.createQuery("SELECT m FROM Equipment AS m WHERE m.equipmentType=:rt").setParameter("rt", type).getResultList();
    }

}
