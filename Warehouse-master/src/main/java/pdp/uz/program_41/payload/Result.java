package pdp.uz.program_41.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
private String message;
private boolean active;
private Object object;

    public Result(String message, boolean active) {
        this.message = message;
        this.active = active;
    }

    public Result(Object object) {
        this.object = object;
    }
}



