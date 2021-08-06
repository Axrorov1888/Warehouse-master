package pdp.uz.program_41.entity.template;

import lombok.Data;
import pdp.uz.program_41.entity.Warehouse;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable =false)
    private String name;

    @Column(nullable =false)
    private boolean active =true;
}
