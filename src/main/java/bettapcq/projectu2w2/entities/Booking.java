package bettapcq.projectu2w2.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    @Setter(AccessLevel.NONE)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "date_of_request", nullable = false)
    private LocalDate requestDate;

    private String notes;

    public Booking(Trip trip, Employee employee, LocalDate requestDate, String notes) {
        this.trip = trip;
        this.employee = employee;
        this.requestDate = requestDate;
        this.notes = notes;
    }
}
