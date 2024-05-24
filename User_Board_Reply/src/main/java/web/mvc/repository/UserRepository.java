package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import web.mvc.domain.FreeBoard;
import web.mvc.domain.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {


}
