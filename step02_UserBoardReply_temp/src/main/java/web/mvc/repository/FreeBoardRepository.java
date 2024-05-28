package web.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import web.mvc.domain.FreeBoard;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> ,
        QuerydslPredicateExecutor<FreeBoard> { // Querydsl을 사용시 쿼리에 오류부분을 미리알 수 있다


    /*
    * 연관관계가 있을떄 - findAll() 메소드를 사용하면
    * 부모글을 기준으로 댓글정보 가져오면 (LAZY 전략인경우)
    * 부모글의 개수만큼 댓글의 select를 계쏙 실행된다
    * 그래서 JPQL문법 , QueryDSL를 이용해서 직접 fetch조인을 사용한다
    *
    * */

    @Query(value = "select f from FreeBoard f left join fetch f.repliesList")
    List<FreeBoard> join01();



    //페이징처리/////////////////////////
    @Query(value = "select distinct f from FreeBoard f left join fetch f.repliesList"
            //,countQuery = "select count(distinct f.bno) from FreeBoard f left join fetch f.repliesList"
    )

    Page<FreeBoard> join02(Pageable pageable);


    /////@OneToMany TEST/////////////////////////////////////////////////////////////////////
    /**
     * 그냥join
     * */
    @Query("select f from FreeBoard f  join  f.repliesList")
    List<FreeBoard> join();


    /**
     * join fetch를 이용하여 1 +N을 해결
     *   : 1) select는 한번만 해서 댓글 정보를 가져오지만 FreeBoard의 레코드가 댓글의 개수만만 반복되는 문제 발생
     *   : 2) 그래서 중복행 제거 distinct 사용해보자  - 1)의 이슈를 해결
     *           - 그렇지만 실제로 로그에 찍힌 쿼리를 도구로 돌려보면 1)처럼 실행되내 jpa가 application로 데이터를
     *             로딩해올때 중복을 제거해준다.
     * */
    @Query("select f from FreeBoard f  join fetch f.repliesList")
    List<FreeBoard> join0();


    @Query("select distinct f from FreeBoard f  join fetch f.repliesList")
    List<FreeBoard> join0_1();

    /////////////////////////////////////////////////////////////////
    /**
     * 위의 join0()의 쿼리에 페이징 처리하고싶다.
     * */
    //@Query("select f from FreeBoard f left join fetch f.replyList")//35
    //@Query("select f from FreeBoard f")//30
    @Query("select distinct f from FreeBoard f join fetch f.repliesList")
    List<FreeBoard> join1(Pageable page);

    // Page를 리턴하기 위해서는 countQuery가 필요하다.
    //@Query(value = "select distinct f from FreeBoard f  join fetch f.replyList" )
    //Page<FreeBoard> join2(Pageable page);

    //
    @Query(value = "select distinct f from FreeBoard f  join fetch f.repliesList",
            countQuery = "select count( f.bno) from FreeBoard f join f.repliesList" )
    Page<FreeBoard> join2_1(Pageable page);


}














