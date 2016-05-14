/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Booking;
import entity.Facility;
import entity.Member1;
import java.util.List;

/**
 *
 * @author ahas36
 */
public interface BookingDao extends Dao<Booking, Integer>{
    List<Booking> getAllBookingsByMember(Member1 member);
    void cancelSubBooking(Booking b);
}
