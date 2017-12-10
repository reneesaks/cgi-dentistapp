package com.cgi.dentistapp.dao;

import com.cgi.dentistapp.dao.entity.DentistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DentistDao extends CrudRepository<DentistEntity, Long>
{
    DentistEntity findByDentistId(Long id);

    List<DentistEntity> findAll();
}
