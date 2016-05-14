/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Booking;
import entity.Facility;
import entity.Member1;
import entity.SubBooking;
import java.util.List;

/**
 *
 * @author ahas36
 */
public class JpaSubBookingDao extends JpaDao<SubBooking, Integer> implements SubBookingDao {

    public JpaSubBookingDao() {
        super(SubBooking.class);
    }

    @Override
    public void cancelSubBooking(SubBooking sb) {
        sb.setSubBookingSatuts("inactive");
        super.update(sb);
    }

    public List<SubBooking> getAllActiveSubBookingsByMember(Member1 member) {
        if(member==null)
        {
            return entityManager.createQuery("SELECT m from SubBooking as m where m.subBookingSatuts='active'")
                .getResultList();
        }
        return entityManager.createQuery("SELECT m from SubBooking as m where m.subBookingSatuts='active' and m.bookingId.memberId=:m").
                setParameter("m", member).getResultList();
    }

}
