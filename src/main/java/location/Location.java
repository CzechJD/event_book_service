package location;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Location {
    private Integer id;
    private String name;
    private String address;
    private Integer capacity;
    private String description;
}
