package web.mvc;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import web.mvc.domain.FreeBoard;
import web.mvc.repository.FreeboardRepository;
import web.mvc.service.FreeBoardService;

@SpringBootTest
//@DataJpaTest    //@DataJpaTest // 스프링에서 jpa에 관련된 테스트 설정만 로드하여 실행한다
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
@Transactional
@Commit
public class FreeBoardTest {

    @Autowired
    private FreeboardRepository freeboardRepository;

    @Autowired
    private FreeBoardService freeBoardService;

    @Test
    public void insert(){
        for(int i = 1; i <= 50; i++){
            freeboardRepository.save(FreeBoard.builder()
                            .subject("subject"+i)
                            .writer("writer"+i)
                            .content("content"+i)
                            .password("123")
                            .build());



        }
             log.info("등록성공");


    }


}
