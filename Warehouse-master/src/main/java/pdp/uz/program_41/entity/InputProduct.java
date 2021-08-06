package pdp.uz.program_41.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputProduct {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

@ManyToOne
private Product product;

@Column(nullable =false)
    private Double amount;

@Column(nullable=false)
    private Double price;

@Column(nullable =false)
    private Date expireDate;

@ManyToOne
private Input input;

}
