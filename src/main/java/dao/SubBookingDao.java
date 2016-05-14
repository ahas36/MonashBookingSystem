/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Booking;
import entity.Facility;
import entity.SubBooking;
import java.util.List;

/**
 *
 * @author ahas36
 */
public interface SubBookingDao extends Dao<SubBooking, Integer>{
    void cancelSubBooking(SubBooking sb);
}
