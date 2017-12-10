package com.cgi.dentistapp;

import com.cgi.dentistapp.dao.entity.DentistEntity;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.validation.BindingResult;

public class ValidateVisit
{
    private DentistVisitDTO dentistVisitDTO;
    private DentistVisitService dentistVisitService;
    private DentistService dentistService;
    private BindingResult bindingResult;

    public ValidateVisit(DentistVisitDTO dentistVisitDTO, DentistVisitService dentistVisitService, DentistService dentistService, BindingResult bindingResult)
    {
        this.dentistVisitDTO = dentistVisitDTO;
        this.dentistVisitService = dentistVisitService;
        this.dentistService = dentistService;
        this.bindingResult = bindingResult;
        this.isValid();
    }

    private void isValid()
    {

        if (!bindingResult.hasErrors()) {

            if (dentistService.getDentistByDentistId(dentistVisitDTO.getDentistNameId()) == null) {

                bindingResult.rejectValue("dentistNameId", "dentistNotFound", "Dentist not found");

            } else {

                DentistEntity dentistEntity = dentistService.getDentistByDentistId(dentistVisitDTO.getDentistNameId());

                // Time validation
                DateUtil requestedDate = new DateUtil(dentistVisitDTO.getVisitTime());

                if (requestedDate.isTooSoon()) {
                    bindingResult.rejectValue("visitTime", "timeIsTooSoon", "Time must be at least 3 hours in advance");
                }

                if (!requestedDate.isAllowedToBook()) {
                    bindingResult.rejectValue("visitTime", "timeIsGreaterThanAllowed", "Time can not be greater than 60 days");
                }

                if (requestedDate.isInPast()) {
                    bindingResult.rejectValue("visitTime", "timeIsInPast", "Time can not be in past");
                }

                if (!requestedDate.isInWorkingHours()) {
                    bindingResult.rejectValue("visitTime", "timeIsOutsideOfWorkingHours", "Time is outside of working hours");
                }

                for (int x = 0; x < dentistVisitService.getAllBookings().size(); x += 1) {
                    Long timeDifference = dentistVisitDTO.getVisitTime().getTime() - dentistVisitService.getAllBookings().get(x).getVisitTime().getTime();
                    int timeDifferenceInMinutes = (int) Math.abs(timeDifference / 1000 / 60);

                    String currentDentistName = dentistVisitService.getAllBookings().get(x).getDentistEntity().getDentistName();
                    String requestedDentistName = dentistEntity.getDentistName();

                    if (currentDentistName.equals(requestedDentistName) && timeDifferenceInMinutes < 60) {
                        bindingResult.rejectValue("visitTime", "visitTimeConflict", "Time error");
                    }
                }

            }
        }
    }

}
