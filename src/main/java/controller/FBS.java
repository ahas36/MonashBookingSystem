/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;
import dao.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ahas36
 */
public class FBS {

    JpaAdministratorDao adminDao;
    JpaBookingDao bookingDao;
    JpaEquipmentDao equipDao;
    JpaFacilityDao facilityDao;
    JpaMemberDao memberDao;
    JpaRoomDao roomDao;
    JpaSubBookingDao subBookingDao;

    public FBS() {
        adminDao = new JpaAdministratorDao();
        bookingDao = new JpaBookingDao();
        equipDao = new JpaEquipmentDao();
        facilityDao = new JpaFacilityDao();
        memberDao = new JpaMemberDao();
        roomDao = new JpaRoomDao();
        subBookingDao = new JpaSubBookingDao();
    }

    public Facility searchFacilityByID(int facilityID) {
        return facilityDao.findById(facilityID);
    }

    public boolean login(String userName, String password) {
        return adminDao.isExsist(userName, password);
    }

    public Administrator findAdminByUsername(String id) {
        return adminDao.findById(id);
    }

    public Member1 findMemberByID(int id) {
        return memberDao.findById(id);
    }

    public Equipment[] getAllEquipmentsByType(String type) {
        List<Equipment> allEquipmentsByType = equipDao.getAllEquipmentsByType(type);
        return allEquipmentsByType.toArray(new Equipment[allEquipmentsByType.size()]);
    }

    public Room[] getAllRoomsByType(String type) {
        List<Room> allRomeByType = roomDao.getAllRomeByType(type);
        return allRomeByType.toArray(new Room[allRomeByType.size()]);
    }

    public String addSubBooking(Booking b, SubBooking sb) {
        long diffInMillies = sb.getEndDate().getTime() - sb.getStartDate().getTime();
        long mins = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (mins % (sb.getFacilityId().getBookingDurationUnit() * 60) != 0) {
            return "Duration should be devideable by " + sb.getFacilityId().getBookingDurationUnit() + "h";
        }
        if (mins > (sb.getFacilityId().getMaxBooking() * sb.getFacilityId().getBookingDurationUnit() * 60)) {
            return "duration should be less than " + (sb.getFacilityId().getMaxBooking() * sb.getFacilityId().getBookingDurationUnit()) + "h";
        }
        List<SubBooking> subBookings = facilityDao.isFacilityAvailable(sb.getFacilityId(), sb.getStartDate(), sb.getEndDate());
        if (!subBookings.isEmpty()) {
            String res = "Facility " + sb.getFacilityId() + " is booked in the following dates: \n";
            for (SubBooking subbooking : subBookings) {
                res += "from " + subbooking.getStartDate() + " to " + subbooking.getEndDate() + "\n";
            }
            return res;
        }
        if (b.getSubBookingList() != null) {
            boolean flag=false;
            String res = "Facility " + sb.getFacilityId() + " has conflict with the following entered date(s): \n";
            for (SubBooking subBookin : b.getSubBookingList()) {
                if ((subBookin.getStartDate().getTime() > sb.getStartDate().getTime() && subBookin.getStartDate().getTime() < sb.getEndDate().getTime())
                        || (subBookin.getEndDate().getTime() > sb.getStartDate().getTime() && subBookin.getEndDate().getTime() < sb.getEndDate().getTime())
                        || (subBookin.getStartDate().getTime() <= sb.getStartDate().getTime() && subBookin.getEndDate().getTime() >= sb.getEndDate().getTime())) {

                    res += "from " + subBookin.getStartDate() + " to " + subBookin.getEndDate() + "\n";
                    flag=true;
                }
            }
            if(flag)
            {
                return res;
            }
        }
        b.addSubBooking(sb);
        return "";
    }
    public void CreateBooking(Booking b)
    {
        List<SubBooking> sbs=new ArrayList<>(b.getSubBookingList());
        b.setSubBookingList(null);
        Booking created = bookingDao.create(b);
        for (SubBooking subBooking : sbs) {
            subBooking.setBookingId(created);
            subBookingDao.create(subBooking);
        }
    }

    public void cancelSubBooking(int id) {
        SubBooking sb = subBookingDao.findById(id);
        sb.setSubBookingSatuts("inactive");
        subBookingDao.update(sb);
    }
    public void removeSubBooking(SubBooking sb) {
        subBookingDao.remove(sb);
        
    }
    
    public List<SubBooking> getAllActiveSubBookingsByMember(Member1 member) {
        return subBookingDao.getAllActiveSubBookingsByMember(member);
    }
    
    public List<Room> findAllAvailableRooms(Date start,Date end)
    {
        return roomDao.getAllAvialableRooms(start, end);
    }
    public List<Equipment> findAllAvailableEquipments(Date start,Date end)
    {
        return equipDao.getAllAvialableEquipments(start, end);
    }
    public List<Facility> findAllAvailableFacilitys(Date start,Date end)
    {
        return facilityDao.getAllAvialableFcilities(start, end);
    }

    public int createMember(String fName, String lname, Date dob, String type) {
        Member1 m=new Member1();
        m.setFirstName(fName);
        m.setLastName(lname);
        m.setDob(dob);
        m.setMemberType(type.toLowerCase());
        memberDao.create(m);
        return m.getMemberId();
    }
}
