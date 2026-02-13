package bettapcq.projectu2w2.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ChangeTripStatusDTO(
        @NotBlank(message = "It Must be a status")
        @Pattern(
                regexp = "COMPLETED|SCHEDULED",
                message = "Status must be COMPLETED or SCHEDULED")
        String status) {
}
