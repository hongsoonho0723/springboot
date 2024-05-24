package web.mvc;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import web.mvc.domain.Board;
import web.mvc.repository.BoardRepository;
import web.mvc.service.BoardService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
//@DataJpaTest    //@DataJpaTest // 스프링에서 jpa에 관련된 테스트 설정만 로드하여 실행한다
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
@Transactional
@Commit
public class BoardRepTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    public void aa(){
      log.info("boardRepository = {}", boardRepository);
      log.info("boardService = {}", boardService);
    }

    @BeforeEach
    public void before(){
        log.info("beforeEach.....");
    }

    @AfterEach
    public void after(){
        log.info("afterEach.....");
    }

    /*
    * 등록
    * */

    @Test
    public void insert(){
       /* for(int i =1; i<=200; i++) {
            boardRepository.save(Board.builder() //save()는 insert와 update를 둘다 가지고있다
                    .title("제목"+i+i)
                    .writer("User"+i+i)
                    .content("내용"+i+i)
                    .build());

        }*/

/*
        Board save = boardRepository.save(Board.builder()
                .bno(201l)
                .title("제목 수정")
                .writer("User수정")
                .content("내용수정")
                .build());*/


        Board board = boardRepository.save(Board.builder() //save()는 insert와 update를 둘다 가지고있다
                .title("jpatest")
                .writer("장희정")
                .content("신기하다")
                .build());

        //Assertions.assertEquals(203, board.getBno());
        log.info("저장된 결과 = {}",board);
        org.assertj.core.api.Assertions.assertThat(board.getBno()).isEqualTo(200);

        log.info("등록완료!!");

    }

    /*
    * 전체검색
    * */
    @Test
    public void selectAll(){
        List<Board> list = boardRepository.findAll();
       /* for(Board b : list){
            System.out.println(b);
        }*/

       /* list.forEach(new Consumer<Board>() {
            @Override
            public void accept(Board board) {
                System.out.println(board);
            }
        });*/
        //람다식
       list.forEach(board -> System.out.println(board));
    }

    /*
     * 조건검색
     * */
    @Test
    public void selectByBno(){
        //Optional java.util에 추가된 객체로 null여부를 체크하지 않아도 되도록
        //관련된 메소드를 풍부하게 재공한다
        Optional<Board> boardOptional = boardRepository.findById(300l);

        //Board result=boardOptional.orElse(Board.builder().title("예외").build());
        Board result=boardOptional.orElse(null);
        System.out.println("---------------------------------");
        System.out.println("result = "+result);
        System.out.println("---------------------------------");
    }

    /*
     * 수정
     * */
    @Test
    public void update(){
        Board board =boardRepository.findById(1L).orElse(null);

        if(board !=null){
            board.setContent("수정1");
            board.setTitle("제목수정");
            board.setWriter("작성자 수정");
        }
    }

    /*
     * 삭제
     * */
    @Test
    public void delete(){
        boardRepository.deleteById(1l);
        //Board board =boardRepository.findById(1L).orElse(null);

    }

    //JPQP문법 (Entity중심으로 쿼리를 작성하는 것) //////////////
    /**
     * 인수로 전달된 글번호보다 큰 레코드 삭제하고싶다
     */
    @Test
    public void delete2(){
        boardRepository.deleteGreateThan(150L);
    }

    /*
    * 글번호 or 제목에 해당하는 레코드 검색
    * */
    @Test
    public void select01(){
        List<Board> list =  boardRepository.selectByBnoTitle(130L,"제목55");
        list.forEach(b -> System.out.println(b));
    }

    /*
    * 글번호, 제목, 작성자에 해당하는 레코드 검색
    * */
    @Test
    public void select02(){
        List<Board> list =  boardRepository.selectByParamsBoard(Board.builder()
                .bno(50L)
                .title("제목3")
                .writer("User3")
                .build());
        list.forEach(b -> System.out.println(b));
    }

    @Test
    public void findByXx(){
        List<Board> list =  boardRepository.findByBnoLessThanAndWriter(50l,"User3");
        list.forEach(b -> System.out.println(b));
    }
    //페이징처리
    @Test
    public void pagging(){
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC,"bno");
        Page<Board> page =boardRepository.findAll(pageable);

        System.out.println("page.getNumber() = "+page.getNumber());
        System.out.println("page.getSize() = "+page.getSize());
        System.out.println("page.getTotalPages() = "+page.getTotalPages());
        System.out.println("page.previousPageable() = "+page.previousPageable());
        System.out.println("page.nextPageable() = "+page.nextPageable());

        System.out.println("page.isFirst() = "+page.isFirst());
        System.out.println("page.isLast() = "+page.isLast());

        System.out.println("page.hasNext() = "+page.hasNext());
        System.out.println("page.hasPrevious() = "+page.hasPrevious());



        List<Board> list = page.getContent();
        list.forEach(b -> System.out.println(b));
    }
















}



