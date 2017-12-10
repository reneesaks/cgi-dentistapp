package com.cgi.dentistapp.dao;

import com.cgi.dentistapp.dao.entity.DentistEntity;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistVisitDao extends CrudRepository<DentistVisitEntity, Long>
{
    Iterable<DentistVisitEntity> findAll();

    DentistVisitEntity findByDentistEntity(DentistEntity dentistEntity);
}
