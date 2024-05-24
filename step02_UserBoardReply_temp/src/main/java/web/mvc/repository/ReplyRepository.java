package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import web.mvc.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> ,
        QuerydslPredicateExecutor<Reply> { //Querydsl 사용이유 쿼리 오류를 미리 알수있다
}
