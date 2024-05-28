package web.mvc;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import web.mvc.domain.FreeBoard;
import web.mvc.domain.QFreeBoard;
import web.mvc.repository.FreeBoardRepository;

import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class BoardJoinTest {

    @Autowired
    private FreeBoardRepository freeRep;

    @Autowired
    private JPAQueryFactory jpaFactory;

    ////@OneToMany join Test////////////////////////////////
    /**
     * 지연로딩으로
     * 먼저, FreeBoard 검색을 한 다음,
     * b.getReplyList().size() 요청으로 인해
     * 각 부모글의 글의 개수만큼 reply테이블의 select을 실행한다 - 성능이슈(많은 select요청)
     *  :해결 방인
     *    1) fetch조인
     *    2)  1)에 distinct적용
     *    3)  @BatchSize(size = 100) 적용 - in연산자 적용

     * */
    @Test
    void join() {
        List<FreeBoard> list=freeRep.findAll(); // FreeBoard 레코드 수 만큼 reply select (1 + N)
        System.out.println("***************************************************");
        System.out.println("list.size = " + list.size());
        System.out.println("댓글....");
        list.forEach(b->System.out.println(b.getBno() +" = " + b.getRepliesList().size()));


    }


    /**
     * 그냥 조인경우
     * 실행결과  조인쿼리를 실행했지만 결과는
     * FreeBoard만 가져온다
     * */
    @Test
    void join00() {
        List<FreeBoard> list=freeRep.join(); // FreeBoard 레코드 수 만큼 reply select (1 + N)
        System.out.println("list.size = " + list.size());
        System.out.println("댓글....");
        list.forEach(b->System.out.println(b.getBno() +" = " + b.getRepliesList().size()));


    }

    /**
     * JPQL fetch join
     * : 조인을 이용하여 데이터를 가져온다. - 댓글이 있는 부모글의 레코드가 중복됨.
     *   select f from FreeBoard f  join fetch f.replyList
     * */
    @Test
    void join0() {
        List<FreeBoard> list = freeRep.join0();
        System.out.println("list.size = " + list.size());
        System.out.println("----------------------------");
        list.forEach(b->System.out.println(b.getBno() +" = " + b.getRepliesList().size()));

		/*list.forEach(b->{0.
			System.out.println(b.getBno()+" | " + b.getSubject());
			b.getReplyList().forEach(r->{
				System.out.println("====> " +r.getRno()+" | " +r.getContent()+" | "+ r.getRno());
			});
			System.out.println();
		});*/
    }

    /**
     * join fetch에 distinct 적용
     *  : select distinct f from FreeBoard f  join fetch f.replyList
     *  : 결과 list.size()를 확인하면 FreeBoard의 중복을 제거 했다.
     * */
    @Test
    void join0_1() {
        List<FreeBoard> list = freeRep.join0_1();
        System.out.println("list.size = " + list.size());
        System.out.println("----------------------------");
        list.forEach(b->System.out.println(b.getBno() +" = " + b.getRepliesList().size()));

        list.forEach(b->{
            System.out.println(b.getBno()+" | " + b.getSubject());
            b.getRepliesList().forEach(r->{
                System.out.println("====> " +r.getRno()+" | " +r.getContent()+" | "+ r.getRno());
            });
            System.out.println();
        });
    }


    /**
     *  JPQL fetch join에 페이징처리  해보자
     * */
    @Test
    void join1() {
        Pageable pageable = PageRequest.of(0,5 , Sort.Direction.ASC , "bno");
        //Pageable pageable = PageRequest.of(0,5 , Sort.Direction.ASC , "subject");

        List<FreeBoard> list = freeRep.join1(pageable);
        System.out.println("list.size = " + list.size());
        list.forEach(b->System.out.println(b.getBno() +" = " + b.getRepliesList().size()));

		list.forEach(b->{
			System.out.println(b.getBno()+" | " + b.getSubject());
			b.getRepliesList().forEach(r->{
				System.out.println("====> " +r.getRno()+" | " +r.getContent()+" | "+ r.getRno());
			});
			System.out.println();
		});
    }

    /**
     * count(distinct f.id) 를 써야 레코드수도 중복 제거
     *
     * */
    @Test
    void join2() {
        Pageable pageable = PageRequest.of(1,2 , Sort.Direction.ASC , "bno");
        //Page<FreeBoard> page = freeRep.join2(pageable);


        //countQuery적용
        Page<FreeBoard> page = freeRep.join2_1(pageable);

        System.out.println("***************************");
        System.out.println("page.getNumber() = "+page.getNumber());
        System.out.println("page.getSize() = "+page.getSize());
        System.out.println("page.getTotalPages() = "+page.getTotalPages());
        System.out.println("page.previousPageable() = "+page.previousPageable());
        System.out.println("page.nextPageable() = "+page.nextPageable());

        System.out.println("page.isFirst() = "+page.isFirst());
        System.out.println("page.isLast() = "+page.isLast());

        System.out.println("page.hasPrevious() = "+page.hasPrevious());
        System.out.println("page.hasNext() = "+page.hasNext());
        System.out.println("*****************************************");


        System.out.println("list.size = " + page.getContent().size());
        //list.forEach(b->System.out.println(b.getBno() +" = " + b.getReplyList().size()));

        page.getContent().forEach(b->{
            System.out.println(b.getBno()+" | " + b.getSubject());
            b.getRepliesList().forEach(r->{
                System.out.println("====> " +r.getRno()+" | " +r.getContent()+" | "+ r.getRno());
            });
            System.out.println();
        });
    }



    //////////////////////////////////////////////////////////////////////

    @Test
    void freeSelectQueryDSL() {
        //
        //BooleanBuilder builder = new BooleanBuilder();
        QFreeBoard board= QFreeBoard.freeBoard;
        //QReply reply = QReply.reply;

        List<FreeBoard> list =
                jpaFactory
                        .selectFrom(board)
                        //.innerJoin(board.photoList)
                        .innerJoin(board.repliesList)
                        .distinct()
                        .fetchJoin()
                        .fetch();


        System.out.println("list.size = " + list.size());
        list.forEach(b->System.out.println(b.getRepliesList().size()));
    }

    ////////////////////////////////////////////


}
