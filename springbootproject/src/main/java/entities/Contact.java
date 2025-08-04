package entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Embeddable
public class Contact {

    @Email(message = "Email invalide")
    private String email;

    @Pattern(regexp = "\\d{9}", message = "Le numéro doit contenir exactement 9 chiffres")
    private String number;

    private String website;
}
