package bettapcq.projectu2w2.controllers;

import bettapcq.projectu2w2.entities.Booking;
import bettapcq.projectu2w2.exceptions.ValidationException;
import bettapcq.projectu2w2.payloads.NewBookingsDTO;
import bettapcq.projectu2w2.services.BookingsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingsService bookingsService;

    public BookingController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    //POST

    @PostMapping
    public Booking addBooking(@RequestBody @Validated NewBookingsDTO payload, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }
        return this.bookingsService.addBoking(payload);
    }


}
