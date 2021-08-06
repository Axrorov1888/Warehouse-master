package pdp.uz.program_41.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.program_41.entity.template.General;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends General {

    @ManyToOne
    private Category category;

    @OneToOne
private Attachment photo;

    @ManyToOne
    private Measurement measurement;

    @Column(nullable =false)
    private String code;
}
