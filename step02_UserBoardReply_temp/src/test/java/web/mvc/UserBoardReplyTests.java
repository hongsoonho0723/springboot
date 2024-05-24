package web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import web.mvc.domain.FreeBoard;
import web.mvc.domain.User;
import web.mvc.repository.FreeBoardRepository;
import web.mvc.repository.UserRepository;

//@SpringBootTest //전체를 test할때 사용
@DataJpaTest // jpa 영속성에 관련된 test / @Componet에 해당하는 부분은 적용안됨.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 설정한 DB사용
@Slf4j

public class UserBoardReplyTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

   /* @Autowired
    private UserService userService;
*/
    @Test
    public void aa(){
        System.out.println("------------------------------------------");
        log.info("userRepository = {}", userRepository);
        System.out.println("------------------------------------------");

    }


    @Test
    @Rollback(value = false)
    public void userInsert(){
        userRepository.save(User.builder().userId("hong").pwd("123").name("순호").build());
        userRepository.save(User.builder().userId("soon").pwd("123").name("홍").build());


    }

    /*
    * 게시물 샘플등록
    * */
  /* @Test
   @Rollback(value = false)
    public void Insert(){
        for(int i = 1; i <= 45; i++){
            freeBoardRepository.save(FreeBoard.builder()
                    .subject("제목"+i)
                    .writer("작성자"+i)
                    .readnum(0)
                    .content("내용"+i)
                    .password("123")
                    .build());



        }
        log.info("등록성공");


    }*/



}
