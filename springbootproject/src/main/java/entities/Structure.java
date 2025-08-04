package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import enumerations.TypeStructure;

import java.util.List;

@Data
@Entity
public class Structure {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Le nom de la structure est obligatoire")
    private String name;

    @NotNull(message = "Le type de structure est obligatoire")
    private TypeStructure type;

    private String description;

    @NotNull(message = "Les informations de contact sont obligatoires")
    @Embedded
    private Contact contact;

    @Embedded
    private OpeningHours openingHours;

    @NotNull(message = "L'adresse est obligatoire")
    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableDoc> availableDocs;
}
