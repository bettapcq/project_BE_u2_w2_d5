package bettapcq.projectu2w2.payloads;

import bettapcq.projectu2w2.entities.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record NewBookingsDTO(@NotBlank(message = "Booking must have an associated trip ")
                             @Positive(message = "Trip id must be a positive number")
                             Long trip,
                             @NotBlank(message = "Booking must have an associated employee ")
                             @Positive(message = "Employee id must be a positive number")
                             Employee employee,
                             @NotBlank(message = "Booking must have a date of request")
                             LocalDate requestDate,
                             @Size(max = 150, message = "Notes can have max 150 characters")
                             String notes) {
}
