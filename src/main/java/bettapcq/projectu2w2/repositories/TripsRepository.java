package bettapcq.projectu2w2.repositories;

import bettapcq.projectu2w2.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TripsRepository extends JpaRepository<Trip, Long> {
    public Trip findByDestinationAndDate(String destination, LocalDate date);
}
