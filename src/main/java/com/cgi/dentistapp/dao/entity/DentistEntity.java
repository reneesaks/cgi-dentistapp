package com.cgi.dentistapp.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dentist")
public class DentistEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dentistId;
    private String dentistName;
    @OneToMany(mappedBy = "dentistEntity", cascade = CascadeType.ALL)
    private Set<DentistVisitEntity> bookings;

    public DentistEntity()
    {
    }

    public Long getDentistId()
    {
        return dentistId;
    }

    public void setDentistId(Long dentistId)
    {
        this.dentistId = dentistId;
    }

    public String getDentistName()
    {
        return dentistName;
    }

    public void setDentistName(String dentistName)
    {
        this.dentistName = dentistName;
    }

    public Set<DentistVisitEntity> getBookings()
    {
        return bookings;
    }

    public void setBookings(Set<DentistVisitEntity> bookings)
    {
        this.bookings = bookings;
    }

}
