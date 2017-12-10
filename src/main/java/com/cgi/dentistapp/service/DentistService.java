package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dao.DentistDao;
import com.cgi.dentistapp.dao.entity.DentistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DentistService
{
    @Autowired
    private DentistDao dentistDao;

    public DentistEntity getDentistByDentistId(Long dentistId)
    {
        return dentistDao.findByDentistId(dentistId);
    }

    public List<DentistEntity> getAllDentists()
    {
        List<DentistEntity> dentists = new ArrayList<>();
        dentists.addAll(dentistDao.findAll());
        return dentists;
    }
}
