package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.mvc.domain.User;


public interface UserRepository extends JpaRepository<User, String> {


}
