package web.mvc.domain;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name = "이름설정..")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "repliesList")
@DynamicUpdate
public class FreeBoard { //db에 free_board
	@Id //pk
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "free_bno_seq")
	@SequenceGenerator(name ="free_bno_seq" , allocationSize = 1 , sequenceName = "free_bno_seq")
	private Long bno;
	private String subject;
	private String writer;
	
	@Column(length = 500)
	private String content;
	private String password;
	private int readnum; //조회수
	
	@CreationTimestamp
	private LocalDateTime insertDate; //등록일
	
	@UpdateTimestamp
	private LocalDateTime updateDate; //수정
	
	
	//부모글 한개에 딸린 댓글 정보 
	/**
	 * cascade sms  Entity 의 상태변화가 생기면 연관관계 있는
	 *  Entity도 상태변화를 전이시키는 옵션
	 *	CascadeType.ALL: 모든 CascadeType을 적용합니다.
	 *	CascadeType.PERSIST: 부모 엔티티를 저장할 때 연관된 자식 엔티티도 함께 저장합니다.
	 * 	CascadeType.MERGE: 부모 엔티티를 병합할 때 연관된 자식 엔티티도 함께 병합합니다.
	 * 	CascadeType.REMOVE: 부모 엔티티를 삭제할 때 연관된 자식 엔티티도 함께 삭제합니다.
	 * 	CascadeType.REFRESH: 부모 엔티티를 새로 고칠 때 연관된 자식 엔티티도 함께 새로 고칩니다.
	 * 	CascadeType.DETACH: 부모 엔티티를 분리할 때 연관된 자식 엔티티도 함께 분리합니다.
	 * */
	@OneToMany(mappedBy = "freeBoard" , cascade = CascadeType.ALL) // 참조되있는걸 지울 때 참조 위배되는걸 설정한다
	//@OneToMany(mappedBy = "freeBoard") //  지연로딩
	private List<Reply> repliesList;
	
	// 댓글 insert할때 필요
	public FreeBoard(Long bno) {
		this.bno=bno;
	}
	
	
  
}











