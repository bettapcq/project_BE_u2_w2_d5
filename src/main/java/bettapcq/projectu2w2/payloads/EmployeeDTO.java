package bettapcq.projectu2w2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(@NotBlank(message = "Employee must have an username")
                          @Size(min = 2, max = 10, message = "Username can have min 2 and max 10 characters")
                          String username,
                          @NotBlank(message = "Employee name is mandatory")
                          @Size(min = 2, max = 40, message = "Name can have min 2 and max 40 characters")
                          String name,
                          @NotBlank(message = "Employee surname is mandatory")
                          @Size(min = 2, max = 40, message = "Surname can have min 2 and max 40 characters")
                          String surname,
                          @NotBlank(message = "Employee must have an email")
                          @Email(message = "Not valid email format")
                          String email) {
}
