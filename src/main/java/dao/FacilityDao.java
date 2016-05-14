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
public interface FacilityDao extends Dao<Facility, Integer>{

    public List<Facility> getAllAvialableFcilities(Date start,Date End);
    List<SubBooking> isFacilityAvailable(Facility facilityId, Date startDate, Date endDate);
}
