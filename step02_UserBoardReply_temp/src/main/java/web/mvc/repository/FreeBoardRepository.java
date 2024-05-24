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
    @Query(value = "select f from FreeBoard f left join fetch f.repliesList"
            ,countQuery = "select count(*) form FreeBoard f left join fetch f.repliesList")
    Page<FreeBoard> join02(Pageable pageable);

}














