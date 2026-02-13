package bettapcq.projectu2w2.repositories;

import bettapcq.projectu2w2.entities.Booking;
import bettapcq.projectu2w2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {
    boolean existsByEmployeeAndTrip_Date(Employee employee, LocalDate date);

}
