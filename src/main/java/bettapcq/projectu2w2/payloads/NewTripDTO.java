package bettapcq.projectu2w2.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewTripDTO(
        @NotBlank(message = "Trip must have a destination")
        @Size(min = 2, max = 30, message = "Destination can have min 2 and max 30 characters")
        String destination,
        @NotBlank(message = "Trip must have a date")
        @Future(message = "Trip date must be in the future")
        LocalDate date) {
}
