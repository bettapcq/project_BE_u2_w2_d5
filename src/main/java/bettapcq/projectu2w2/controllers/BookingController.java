package bettapcq.projectu2w2.controllers;

import bettapcq.projectu2w2.services.EmployeesService;
import bettapcq.projectu2w2.services.TripsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final EmployeesService employeesService;
    private final TripsService tripsService;

    public BookingController(EmployeesService employeesService, TripsService tripsService) {
        this.employeesService = employeesService;
        this.tripsService = tripsService;
    }

    
}
