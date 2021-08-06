package pdp.uz.program_41.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@CreationTimestamp
    private Timestamp timestamp;

@Column(nullable=false)
    private String code;

@Column(nullable=false)
    private Integer factureNumber;

@ManyToOne
    private Warehouse warehouse;

@ManyToOne
    private Currency currency;

@ManyToOne
    private Client client;
}
