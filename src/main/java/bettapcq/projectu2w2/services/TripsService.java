package bettapcq.projectu2w2.services;

import bettapcq.projectu2w2.entities.Trip;
import bettapcq.projectu2w2.exceptions.BadRequestException;
import bettapcq.projectu2w2.exceptions.NotFoundException;
import bettapcq.projectu2w2.payloads.EditTripsDTO;
import bettapcq.projectu2w2.payloads.TripsDTO;
import bettapcq.projectu2w2.repositories.TripsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TripsService {

    private final TripsRepository tripsRepository;

    public TripsService(TripsRepository tripsRepository) {
        this.tripsRepository = tripsRepository;
    }

    public Trip findById(Long tripId) {
        return this.tripsRepository.findById(tripId).orElseThrow(() -> new NotFoundException(tripId));
    }

    public Trip addTrip(TripsDTO payload) {
        Trip found = this.tripsRepository.findByDestinationAndDate(payload.destination(), payload.date());
        //controllo se esiste già un viaggio con stessa data e destinazione.
        if (found != null) throw new BadRequestException("Same trip already exist with id: " + found.getTripId());

        Trip newTrip = new Trip(payload.destination(), payload.date());

        return this.tripsRepository.save(newTrip);
    }

    public Page<Trip> findAll(int page, int size, String orderBy) {
        if (page < 0) page = 0;
        if (size > 100 || size < 0) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.tripsRepository.findAll(pageable);
    }

    public Trip findByIdAndEdit(Long tripId, EditTripsDTO payload) {
        Trip found = this.tripsRepository.findById(tripId).orElseThrow(() -> new NotFoundException(tripId));

        found.setDestination(payload.destination());
        found.setDate(payload.date());
        found.setStatus(payload.status());

        this.tripsRepository.save(found);

        log.info("Trip with id " + found.getTripId() + " has been saved");

        return found;
    }

    public void findByIdAndDelete(Long tripId) {
        Trip found = this.tripsRepository.findById(tripId).orElseThrow(() -> new NotFoundException(tripId));
        this.tripsRepository.delete(found);
    }


}
