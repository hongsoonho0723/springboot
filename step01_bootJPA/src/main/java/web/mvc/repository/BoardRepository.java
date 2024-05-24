package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import web.mvc.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, QuerydslPredicateExecutor {

    /**
     * 인수로 전달된 글번호보다 큰 레코드 삭제하고싶다
     *  : JPQL 문법을 작성할떄는 @Query선언
     *  : DML작업을 할때는 반드시 @Modifying 선언
     */


    @Query(value="delete from Board b where b.bno > ?1 ")
    @Modifying
    void deleteGreateThan(Long bno);

    /*
     * 글번호 or 제목에 해당하는 레코드 검색
     *
     * nativeQuery = ture 옵션은 DB쿼리 중심으로 쿼리를 작성한다
     * */

    //@Query(value = "select b from Board b where b.bno > ?1 or b.title = ?2")
    @Query(value = "select * from board where bno > ?1 or TITLE = ?2" , nativeQuery = true)
    List<Board> selectByBnoTitle(Long bno,String title);


    /*
     * 글번호, 제목, 작성자에 해당하는 레코드 검색
     * */
    @Query("select b from Board b where b.bno = :#{#bo.bno} or b.title = :#{#bo.title} or b.writer= :#{#bo.writer}")
    List<Board> selectByParamsBoard(@Param("bo") Board board);

    
    
    //QueryMethod 작성/////////////////////////////////////////////////////
    
    /*
    * 전달된 글번호보다 작고 전달된 작성자와 동일한 레코드 검색
    * :findByXxx... 시작하는 메소드 작성
    *   https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    *
    * */

    List<Board> findByBnoLessThanAndWriter(Long bno, String write);


}