package com.cgi.dentistapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil
{
    private LocalDateTime ldt;
    private LocalDateTime currentLdt;
    private LocalDate currentDate;
    private LocalDate requestedDate;

    public DateUtil(Date date)
    {
        this.ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        this.currentLdt = LocalDateTime.now();
        this.currentDate = LocalDateTime.now().toLocalDate();
        this.requestedDate = this.ldt.toLocalDate();
    }

    public boolean isInWorkingHours()
    {
        return ldt.getHour() >= 9 && ldt.getHour() <= 18 && !ldt.getDayOfWeek().toString().equals("SUNDAY");
    }

    public boolean isAllowedToBook()
    {
        return ChronoUnit.DAYS.between(this.currentDate, this.requestedDate) < 60 || !(ChronoUnit.HOURS.between(currentLdt, ldt) > 0);
    }

    public boolean isTooSoon()
    {
        return ChronoUnit.HOURS.between(currentLdt, ldt) < 2 && ChronoUnit.HOURS.between(currentLdt, ldt) >= 1;
    }

    public boolean isInPast()
    {
        return ChronoUnit.DAYS.between(this.requestedDate, this.currentDate) >= 0;
    }

    public LocalDateTime getLdt()
    {
        return this.ldt;
    }
}
