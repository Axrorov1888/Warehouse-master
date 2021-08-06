package pdp.uz.program_41.payload;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
private String name;
private String phoneNumber;
private String code;
private String password;
private List<Integer> warehousesId;
}
