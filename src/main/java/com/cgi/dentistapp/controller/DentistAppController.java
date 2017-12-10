package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.ValidateVisit;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter
{

    @Autowired
    private DentistVisitService dentistVisitService;
    @Autowired
    private DentistService dentistService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/bookings").setViewName("bookings");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model)
    {
        model.addAttribute("dentists", dentistService.getAllDentists());
        model.addAttribute("bookings", dentistVisitService.getAllBookings());
        return "register";
    }

    @PostMapping("/")
    public String createBooking(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model)
    {
        model.addAttribute("dentists", dentistService.getAllDentists());
        model.addAttribute("bookings", dentistVisitService.getAllBookings());
        ValidateVisit validateVisit = new ValidateVisit(dentistVisitDTO, dentistVisitService, dentistService, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        dentistVisitService.addBooking(dentistService.getDentistByDentistId(dentistVisitDTO.getDentistNameId()), dentistVisitDTO.getVisitTime());
        return "redirect:/bookings";
    }

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public String bookingsList(Model model)
    {
        model.addAttribute("dentists", dentistService.getAllDentists());
        model.addAttribute("bookings", dentistVisitService.getAllBookings());
        return "bookings";
    }

    @PostMapping(value = "/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDentistVisit(@RequestParam(value = "dentistVisitId") Long dentistVisitId)
    {
        dentistVisitService.deleteBooking(dentistVisitId);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> modifyDentistVisit(@Valid DentistVisitDTO dentistVisitDTO,
                                                     BindingResult bindingResult,
                                                     Model model,
                                                     @RequestParam("dentistVisitId") Long dentistVisitId,
                                                     @RequestParam("dentistNameId") Long dentistNameId,
                                                     @RequestParam("visitTime") String visitTime)
    {
        model.addAttribute("dentists", dentistService.getAllDentists());
        model.addAttribute("bookings", dentistVisitService.getAllBookings());
        ValidateVisit validateVisit = new ValidateVisit(dentistVisitDTO, dentistVisitService, dentistService, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.TEXT_PLAIN).body(bindingResult.getFieldError().getCode());
        }

        dentistVisitService.modifyBooking(
                dentistVisitId,
                dentistService.getDentistByDentistId(dentistVisitDTO.getDentistNameId()),
                dentistVisitDTO.getVisitTime());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("OK");
    }

}
