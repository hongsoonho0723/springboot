package web.mvc.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity //jpa관리객체
@Table(name = "users") //테이블이름 변경
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id //pk
   private String userId; // user_id
	
   private String pwd;
   private String name;
}
