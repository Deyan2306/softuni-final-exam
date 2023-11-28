package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.BaseEntity;
import bg.softuni.movieapp.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Entity
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
