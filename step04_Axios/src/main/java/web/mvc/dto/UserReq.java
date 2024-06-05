package web.mvc.dto;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {

    private String name;
    private String email;
    private int age;


}
