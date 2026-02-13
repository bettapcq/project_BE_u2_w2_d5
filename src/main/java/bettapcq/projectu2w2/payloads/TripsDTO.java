package bettapcq.projectu2w2.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TripsDTO(
        @NotBlank(message = "Trip must have a destination")
        @Size(min = 2, max = 30, message = "Destination can have min 2 and max 30 characters")
        String destination,
        @NotNull(message = "Trip must have a date")
        @Future(message = "Trip date must be in the future")
        LocalDate date,
        @NotBlank(message = "Trip must have a status")
        @Pattern(
                regexp = "COMPLETED|SCHEDULED",
                message = "Status must be COMPLETED or SCHEDULED")
        String status) {
}
