package web.mvc;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import web.mvc.domain.Board;
import web.mvc.domain.QBoard;
import web.mvc.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
@Slf4j
public class BoardReqQueryDSLTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void init(){
      log.info("boardRepository={}", boardRepository);
      log.info("jpaQueryFactory={}", jpaQueryFactory);
    }

    /*
    * QueryDSL 사용하기
    * interface에 QueryPredicateExecutor<>상속받는다
    * */

    @Test
    public void test01(){
        //조건에 따른 검색
        BooleanBuilder builder = new BooleanBuilder(); //조건을 만들때 사용한것 (where
        QBoard board = QBoard.board;

        //builder.and(board.bno.eq(2L)); //where bno = 2
        builder.and(board.writer.like("%1%"));

        //1) 날짜 시작 ~ 끝
        LocalDateTime from = LocalDateTime.of(2024,1,23,0,0,0);
        LocalDateTime to = LocalDateTime.of(2024,6,28,12,0,0);
        builder.and(board.insertDate.between(from, to));
        /*
        Hibernate: select b1_0.bno,b1_0.content,b1_0.insert_date,b1_0.title,b1_0.update_date,b1_0.writer
        from board b1_0 where b1_0.writer like ? escape '!' and b1_0.insert_date between ? and ?
        */

        //2)
        //builder.and(board.writer.eq("user5")); //대소문자구분한다
        /*
        Hibernate: select b1_0.bno,b1_0.content,b1_0.insert_date,b1_0.title,b1_0.update_date,b1_0.writer
        from board b1_0 where b1_0.writer like ? escape '!' and b1_0.writer=?
        */

        //3)
        //builder.and(board.writer.equalsIgnoreCase("user5")); //대소문자구분 안한다
        /*
        Hibernate: select b1_0.bno,b1_0.content,b1_0.insert_date,b1_0.title,b1_0.update_date,b1_0.writer
        from board b1_0 where b1_0.writer like ? escape '!' and lower(b1_0.writer)=?
        */

        //4)
        //builder.and(board.writer.toUpperCase().eq("user2".toUpperCase())); //대소문자구분 안한다
        /*
        Hibernate: select b1_0.bno,b1_0.content,b1_0.insert_date,b1_0.title,b1_0.update_date,b1_0.writer
         from board b1_0 where b1_0.writer like ? escape '!' and upper(b1_0.writer)=?
        */

        //5)
        //builder.and(board.writer.toUpperCase().eq("user1".toUpperCase())).or(board.bno.gt(140l));
        /*
        Hibernate: select b1_0.bno,b1_0.content,b1_0.insert_date,b1_0.title,b1_0.update_date,b1_0.writer
        from board b1_0 where b1_0.writer like ? escape '!' and upper(b1_0.writer)=? or b1_0.bno>?
        */

        Iterable<Board> iterable = boardRepository.findAll(builder);

        System.out.println("----------------------------------------------");
        //Iterable타입을 바로 list로
        List<Board> list = Lists.newArrayList(iterable);

        list.forEach(b -> System.out.println(b));
        System.out.println("----------------------------------------------");
    }

    /**
     * JPAQueryFactory 사용하기
     */

    @Test
    public void test02(){
        //JPAQueryFactory queryFactory = new JPAQueryFactory(); //@Bean으로 대체
        QBoard board = QBoard.board;
        //삭제
        /*jpaQueryFactory
                .delete(board)
                .where(board.bno.gt(100L))
                .execute();*/

        //수정
        jpaQueryFactory
                .update(board)
                .set(board.writer,"얼짱")
                .set(board.title,"queryFactory연습")
                .where(board.bno.eq(2l))
                .execute();


    }




}
















