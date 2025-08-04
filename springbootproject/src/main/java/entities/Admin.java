package entities;

import config.Role;
import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Admin extends User {

    public Admin(){
        this.setRole(Role.ADMIN);
    }

}
