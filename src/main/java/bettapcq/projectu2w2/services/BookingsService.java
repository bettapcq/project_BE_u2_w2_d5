package bettapcq.projectu2w2.services;

import bettapcq.projectu2w2.entities.Booking;
import bettapcq.projectu2w2.entities.Employee;
import bettapcq.projectu2w2.entities.Trip;
import bettapcq.projectu2w2.exceptions.BadRequestException;
import bettapcq.projectu2w2.payloads.NewBookingsDTO;
import bettapcq.projectu2w2.repositories.BookingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingsService {

    private final EmployeesService employeesService;
    private final TripsService tripsService;
    private final BookingsRepository bookingsRepository;

    @Autowired
    public BookingsService(EmployeesService employeesService, TripsService tripsService, BookingsRepository bookingsRepository) {
        this.employeesService = employeesService;
        this.tripsService = tripsService;
        this.bookingsRepository = bookingsRepository;
    }

    public Booking addBoking(NewBookingsDTO payload) {
        //coontrollo esistenza degli id:
        Employee employeeFound = this.employeesService.findById(payload.employeeId());
        Trip tripFound = this.tripsService.findById(payload.tripId());

        //controllo se l'employee ha già una prenotazione nella data del viaggio associato

        if (bookingsRepository.existsByEmployeeAndTrip_Date(employeeFound, tripFound.getDate()))
            throw new BadRequestException("This employee already has a booking on this date");

        Booking newBooking = new Booking(tripFound, employeeFound, payload.requestDate(), payload.notes());

        Booking newBookingSaved = this.bookingsRepository.save(newBooking);

        log.info("New booking with id " + newBookingSaved.getBookingId() + " has been saved");

        return newBookingSaved;
    }


}

