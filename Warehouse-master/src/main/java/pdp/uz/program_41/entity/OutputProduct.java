package pdp.uz.program_41.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OutputProduct {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

@ManyToOne
    private Product product;

@Column(nullable=false)
    private Double amount;

@Column(nullable =false)
    private Double price;

@ManyToOne
    private Output output;



}
