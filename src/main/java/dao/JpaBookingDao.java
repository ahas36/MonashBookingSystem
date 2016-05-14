/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Administrator;
import entity.Booking;
import entity.Member1;
import entity.SubBooking;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ahas36
 */
public class JpaBookingDao extends JpaDao<Booking, Integer> implements BookingDao{

    public JpaBookingDao() {
        super(Booking.class);
    }

    @Override
    public List<Booking> getAllBookingsByMember(Member1 member) {
        Query q = entityManager.createQuery("SELECT m FROM Booking AS m WHERE m.memberId:=member");
        q.setParameter("member", member);
        return q.getResultList();
    }

    @Override
    public void cancelSubBooking(Booking b) {
        for (SubBooking subBooking : b.getSubBookingList()) {
            subBooking.setSubBookingSatuts("inactive");
            entityManager.merge(subBooking);
        }
    }
    
}
