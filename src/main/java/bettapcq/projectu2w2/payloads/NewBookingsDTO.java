package bettapcq.projectu2w2.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record NewBookingsDTO(@NotNull(message = "Booking must have an associated trip ")
                             @Positive(message = "Trip id must be a positive number")
                             Long tripId,
                             @NotNull(message = "Booking must have an associated employee ")
                             @Positive(message = "Employee id must be a positive number")
                             Long employeeId,
                             @PastOrPresent(message = "Date must be past or present ")
                             LocalDate requestDate,
                             @Size(max = 150, message = "Notes can have max 150 characters")
                             String notes) {
}
