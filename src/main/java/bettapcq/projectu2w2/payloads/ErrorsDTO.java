package bettapcq.projectu2w2.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message,
                        LocalDateTime timestamp) {
}
