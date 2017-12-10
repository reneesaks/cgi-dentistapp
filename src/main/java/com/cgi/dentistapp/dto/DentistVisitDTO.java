package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DentistVisitDTO
{
    @NotNull
    Long dentistNameId;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH")
    Date visitTime;

    public DentistVisitDTO()
    {
    }

    public Long getDentistNameId()
    {
        return dentistNameId;
    }

    public void setDentistNameId(Long dentistNameId)
    {
        this.dentistNameId = dentistNameId;
    }

    public Date getVisitTime()
    {
        return visitTime;
    }

    public void setVisitTime(Date visitTime)
    {
        this.visitTime = visitTime;
    }
}
