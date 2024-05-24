package web.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import web.mvc.domain.FreeBoard;
import web.mvc.domain.QFreeBoard;
import web.mvc.domain.QReply;
import web.mvc.domain.User;
import web.mvc.repository.FreeBoardRepository;
import web.mvc.repository.UserRepository;
import web.mvc.service.UserService;

//@SpringBootTest //전체를 test할때 사용
@DataJpaTest //jpa영속성에 관련된 test / @Component에 해당하는 부분은 적용안됨.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//설정한 Db사용
@Slf4j
public class UserBoardReplyTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

   /* @Autowired
    private UserService userService;*/

    @Test
    public void aa(){
        log.info("userRepository = {}" , userRepository);
        //log.info("userService = {}" , userService);
    }

    /**
     * 회원등록
     * */
    @Test
    @Rollback(value = false)
    public void userInsert(){
        userRepository.save(User.builder().userId("jang").pwd("1234").name("장희정").build());
        userRepository.save(User.builder().userId("kim").pwd("1234").name("김희선").build());
    }

    /**
     * 게시물 샘플등록
     * */
    @Test
    @Rollback(value = false)
    public void boardInsert() {
         for(int i=1; i<=45; i++){
             freeBoardRepository.save(FreeBoard.builder()
                     .subject("제목"+i)
                     .writer("User"+i)
                     .readnum(0)
                     .content("내용"+i)
                     .password("1234")
                     .build());
         }
    }


/////join쿼리 //////////////////////////
    /**
     * 조인쿼리
     * */
    @Test
    public void  join() {
        //QFreeBoard board = QFreeBoard.freeBoard;
        //QReply reply = QReply.reply;

		/*List<FreeBoard>  list =
        queryFactory
		.selectFrom(board)
		.join(board.repliesList, reply)
		.where(board.bno.eq(1L))
		.fetch();

		list.forEach(b->System.out.println(b));*/


	/*	FreeBoard  fb  =
		        queryFactory
				.selectFrom(board)
				.join(board.repliesList, reply) //join을 하고 다시 reply select 쿼리 두번
				.where(board.bno.eq(1L))
				.fetchOne();

		System.out.println(fb);*/


        //////DTO로 리턴 받기///////////////////
		/*List<FreeBoardDTO>  list =
		        queryFactory
				.select(Projections.fields(FreeBoardDTO.class,
						board.bno, board.subject,board.writer, board.content, board.password,
						board.readnum, board.insertDate, reply.rno, reply.content, reply.insertDate))
				.from(board)
				.join(board.repliesList, reply)
				.where(board.bno.eq(1L))
				.fetch();

				list.forEach(b->System.out.println(b.getRepliesList()));*/




		/*FreeBoardDTO dto = queryFactory
			    .select(Projections.fields(FreeBoardDTO.class,
			        board.bno, board.subject, board.writer, board.content, board.password,
			        board.readnum, board.insertDate,
			        Expressions.list( // repliesList를 매핑
			            Projections.fields(ReplyDTO.class,
			                reply.rno, reply.content, reply.insertDate)
			        )
			    ))
			    .from(board)
			    .join(board.repliesList, reply)
			    .where(board.bno.eq(1L))
			    .fetchFirst();*/

        QFreeBoard board = QFreeBoard.freeBoard;
        QReply reply = QReply.reply;

		/*FreeBoardDTO dto = queryFactory
			    .select(Projections.fields(
			    		FreeBoardDTO.class,
			        board.bno.as("bno"),
			        board.subject.as("subject"),
			        board.writer.as("writer"),
			        board.content.as("content"),
			        board.password.as("password"),
			        board.readnum.as("readnum"),
			        board.insertDate.as("insertDate")
			        ,
			        Expressions.list(
				        Projections.constructor(ReplyDTO.class,
				        		reply.rno.as("rno"),
				                reply.content.as("content"),
				                reply.insertDate.as("insertDate")
				            ).as("repliesList") // DTO 속성에 매핑
			        )
			    ))
			    .from(board)
			    .leftJoin(board.repliesList, reply)
			    .where(board.bno.eq(1L))
			    .fetchFirst();*/

		/*FreeBoardDTO dto = queryFactory
			    .select(Projections.fields(
			        FreeBoardDTO.class,
			        board.bno.as("bno"),
			        board.subject.as("subject"),
			        board.writer.as("writer"),
			        board.content.as("content"),
			        board.password.as("password"),
			        board.readnum.as("readnum"),
			        board.insertDate.as("insertDate")
			    ))
			    .from(board)
			    .leftJoin(board.repliesList, reply)
			    .where(board.bno.eq(1L))
			    .fetchFirst();*/

        //dto.addReply( dto.getRepliesList() );


		/*FreeBoard freeBoard =queryFactory
	    .selectFrom(board)
	    .join(board.repliesList, reply)
	    .where(board.bno.eq(1L))
	    .fetchOne();

		  System.out.println(freeBoard +" , " + freeBoard.getRepliesList());*/
    }
}
