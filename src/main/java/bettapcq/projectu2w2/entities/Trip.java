package bettapcq.projectu2w2.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "trips")
@NoArgsConstructor
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    @Setter(AccessLevel.NONE)
    private Long tripId;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDate date;


    private String status;

    public Trip(String destination, LocalDate date) {
        this.destination = destination;
        this.date = date;
        this.status = "SCHEDULED";
    }
}
