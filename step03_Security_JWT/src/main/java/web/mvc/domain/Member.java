package web.mvc.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator =
            "member_no")
    @SequenceGenerator(allocationSize = 1, sequenceName = "member_no" , name
            = "member_no")
    private Long memberNo ;

    @Column(unique = true)
    private String id;

    private String pwd;

    @Column(length = 20)
    private String name;
    private String address;

    private String role;
    //role은 ROLE_USER설정
    @CreationTimestamp
    private LocalDateTime regDate;
}