package web.mvc.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Board {


    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "board_bno_seq")
    @SequenceGenerator(name="board_bno_seq" , sequenceName = "board_bno_seq", allocationSize = 1)
    @Id
    private Long bno;

    @Column(nullable = false)
    private String title;

    @Column(length = 10)
    private String writer;

    private String content;

    @CreationTimestamp
    private LocalDateTime insertDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;




}
