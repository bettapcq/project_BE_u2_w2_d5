package bettapcq.projectu2w2.repositories;

import bettapcq.projectu2w2.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {

}
