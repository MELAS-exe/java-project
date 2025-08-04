package entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @NotBlank(message = "La région est obligatoire")
    private String region;

    @NotBlank(message = "La ville est obligatoire")
    private String city;

    @NotBlank(message = "L'adresse complète est obligatoire")
    private String fullAddress;

    @NotNull(message = "La latitude est obligatoire")
    private Double latitude;

    @NotNull(message = "La longitude est obligatoire")
    private Double longitude;
}
