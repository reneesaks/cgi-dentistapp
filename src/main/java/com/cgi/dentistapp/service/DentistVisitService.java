package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dao.DentistVisitDao;
import com.cgi.dentistapp.dao.entity.DentistEntity;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DentistVisitService
{

    @Autowired
    private DentistVisitDao dentistVisitDao;

    public void addBooking(DentistEntity dentistEntity, Date visitTime)
    {
        DentistVisitEntity booking = new DentistVisitEntity(dentistEntity, visitTime);
        dentistVisitDao.save(booking);
    }

    public void modifyBooking(Long dentistVisitId, DentistEntity dentistEntity, Date visitTime)
    {
        DentistVisitEntity booking = new DentistVisitEntity(dentistEntity, visitTime);
        booking.setDentistVisitId(dentistVisitId);
        dentistVisitDao.save(booking);
    }

    public List<DentistVisitEntity> getAllBookings()
    {
        List<DentistVisitEntity> bookings = new ArrayList<>();
        bookings.addAll((Collection<? extends DentistVisitEntity>) dentistVisitDao.findAll());
        return bookings;
    }

    public void deleteBooking(Long dentistVisitId)
    {
        dentistVisitDao.delete(dentistVisitId);
    }

    public void getVisitByDentist(DentistEntity dentistEntity)
    {
        dentistVisitDao.findByDentistEntity(dentistEntity);
    }

}
