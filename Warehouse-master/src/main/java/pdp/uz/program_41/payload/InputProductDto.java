package pdp.uz.program_41.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {
private Double amount;
private Double price;
private Date expireDate;
private Integer productId;
private Integer inputId;




}
