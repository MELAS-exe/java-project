package entities;

import enumerations.DocumentType;
import jakarta.persistence.*;
        import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.time.Duration;

@Data
@Entity
public class AvailableDoc {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Le type de document est obligatoire")
    @Enumerated(EnumType.STRING)
    private DocumentType type;        // Le type du document (voir l'énumeration DocumentType)

    private String description;       // Informations générales sur le document

    @PositiveOrZero(message = "Les frais doivent être positifs ou nuls")
    private Double fee;               // Prix que la personne doit payer pour obtenir le document

    private Duration processingTime;  // Temps que la structure prend pour livrer le document après la demande

    private String requirements;      // Conditions nécessaires pour faire la demande du document
}
