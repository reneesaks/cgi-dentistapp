package com.cgi.dentistapp.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dentistVisitId;
    private Date visitTime;
    @ManyToOne
    private DentistEntity dentistEntity;

    public DentistVisitEntity()
    {
    }

    public DentistVisitEntity(DentistEntity dentistEntity, Date visitTime)
    {
        this.setVisitTime(visitTime);
        this.dentistEntity = dentistEntity;
    }

    public Long getDentistVisitId()
    {
        return dentistVisitId;
    }

    public void setDentistVisitId(Long dentistVisitId)
    {
        this.dentistVisitId = dentistVisitId;
    }

    public Date getVisitTime()
    {
        return visitTime;
    }

    public void setVisitTime(Date visitTime)
    {
        this.visitTime = visitTime;
    }

    @JoinColumn(name = "dentist_name_id")
    public DentistEntity getDentistEntity()
    {
        return dentistEntity;
    }

    public void setDentistEntity(DentistEntity dentistEntity)
    {
        this.dentistEntity = dentistEntity;
    }

}
