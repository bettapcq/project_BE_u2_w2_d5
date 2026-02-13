package bettapcq.projectu2w2.controllers;

import bettapcq.projectu2w2.entities.Trip;
import bettapcq.projectu2w2.exceptions.ValidationException;
import bettapcq.projectu2w2.payloads.EditTripsDTO;
import bettapcq.projectu2w2.payloads.TripsDTO;
import bettapcq.projectu2w2.services.TripsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip addTrip(@RequestBody @Validated TripsDTO payload, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }

        return this.tripsService.addTrip(payload);
    }

    //GET ALL
    @GetMapping
    public Page<Trip> findAllTrips(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "date") String orderBy) {

        return this.tripsService.findAll(page, size, orderBy);
    }

    //GET BY ID
    @GetMapping("/{tripId}")
    public Trip findTripById(@PathVariable Long tripId) {
        return this.tripsService.findById(tripId);
    }

    //PUT BY ID
    @PutMapping("/{tripId}")
    public Trip editTrip(@RequestBody EditTripsDTO payload, @PathVariable Long tripId, BindingResult valRes) {

        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }

        return this.tripsService.findByIdAndEdit(tripId, payload);
    }

    //DELETE BY ID
    @DeleteMapping("/{tripId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable Long tripId) {
        this.tripsService.findByIdAndDelete(tripId);
    }

}
