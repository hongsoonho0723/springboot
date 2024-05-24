package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.mvc.domain.FreeBoard;
import web.mvc.domain.User;

import java.util.List;

@Repository
public interface FreeboardRepository extends JpaRepository<FreeBoard,Long> {




    @Query(value = "select *from Free_Board", nativeQuery = true)
    List<FreeBoard> selectAll();

}
