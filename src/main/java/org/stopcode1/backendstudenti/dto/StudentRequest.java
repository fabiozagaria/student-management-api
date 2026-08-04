package org.stopcode1.backendstudenti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record StudentRequest(

        @NotBlank(message = "Nome obbligatorio")
        String firstName,

        @NotBlank(message = "Cognome obbligatorio")
        String lastName,

        @NotBlank(message = "Matricola obbligatorio")
        @Pattern(
                regexp = "^[0-9]{8,}$",
                message = "La matricola deve contenere solo numeri e deve essere lunga almeno 8 caratteri"
        )
        String matricola,

        @NotNull(message = "Eta obbligatorio")
        @Min(18)
        Integer age,

        @NotBlank(message = "Universita obbligatoria")
        String university
) {}
