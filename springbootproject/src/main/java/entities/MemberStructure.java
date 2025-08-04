package entities;

import config.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class MemberStructure extends User {

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @NotNull(message = "La structure est obligatoire")
    @ManyToOne
    private Structure structure;

    @NotBlank(message = "Le rôle dans la structure est obligatoire")
    private String RoleInStructure;

    public MemberStructure(){
        this.setRole(Role.MEMBRE_STRUCTURE);
    }
}
