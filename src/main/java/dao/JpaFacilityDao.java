/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Facility;
import entity.SubBooking;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ahas36
 */
public class JpaFacilityDao extends JpaDao<Facility, Integer> implements FacilityDao {

    public JpaFacilityDao() {
        super(Facility.class);
    }

    @Override
    public List<Facility> getAllAvialableFcilities(Date start, Date end) {
        return entityManager.createQuery("Select nr from Facility as nr where nr not in (SELECT r FROM Facility AS r  INNER JOIN SubBooking AS sb on r =sb.facilityId WHERE sb.subBookingSatuts='active' and ((sb.endDate BETWEEN :start and :end) or (sb.startDate BETWEEN :start and :end) or (sb.startDate<=:start and sb.endDate>=:end)))")
                .setParameter("start", start).setParameter("end", end).getResultList();

    }

    @Override
    public List<SubBooking> isFacilityAvailable(Facility facilityId, Date startDate, Date endDate) {
        return entityManager.createQuery("SELECT sb FROM SubBooking as sb WHERE sb.subBookingSatuts='active' and sb.facilityId.facilityId=:id and ((sb.endDate BETWEEN :start and :end) or (sb.startDate BETWEEN :start and :end) or (sb.startDate<=:start and sb.endDate>=:end))")
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .setParameter("id", facilityId.getFacilityId())
                .getResultList();
    }

}
