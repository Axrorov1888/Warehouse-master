package pdp.uz.program_41.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.program_41.entity.template.General;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends General {
}
