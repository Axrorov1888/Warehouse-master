package pdp.uz.program_41.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.program_41.entity.template.General;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends General {

@Column(nullable =false)
    private String phoneNumber;

@Column(nullable =false)
    private String code;

@Column(nullable =false)
    private String password;

    @ManyToMany
    private List<Warehouse> warehouse;
}
