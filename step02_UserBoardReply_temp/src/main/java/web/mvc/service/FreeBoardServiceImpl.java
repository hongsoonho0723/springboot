package web.mvc.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.mvc.domain.FreeBoard;
import web.mvc.exception.BasicException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.FreeBoardRepository;

import java.util.List;

@Service
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService {

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    
    /**
     * 단순 FreeBoard 전체 검색 페이징 처리 X
     * */
    @Override
    public List<FreeBoard> selectAll() {
        //return FreeBoardRepository.selectAll();

        //////fetch조인 사용하기///////////////
        return freeBoardRepository.join01();


    }




    @Override
    public Page<FreeBoard> selectAll(Pageable pageable) {
        return freeBoardRepository.findAll(pageable); // findAll(pageable)을 쓰면
    }

    @Override
    public FreeBoard insert(FreeBoard board) {

        FreeBoard saveBoard = freeBoardRepository.save(board); //freeBoard
        System.out.println("saveBoard = " + saveBoard);
        return saveBoard;
    }

    /**
     * bno 로 Board 찾고 state로 조회수 올리기
     * */
    @Override
    public FreeBoard selectBy(Long bno, boolean state) {

        FreeBoard board = freeBoardRepository.findById(bno)
                .orElseThrow(()->new BasicException(ErrorCode.FAILED_DETAIL)); // 상세보기 오류

        if(state) board.setReadnum(board.getReadnum()+1);
        
        return board;
    }

    /**
     *  board 내용 수정하기
     * */
    @Override
    public FreeBoard update(FreeBoard board) {

        FreeBoard bo = freeBoardRepository.findById(board.getBno())
                .orElseThrow(()->new BasicException(ErrorCode.FAILED_UPDATE));// 업데이트 오류


        if(!bo.getPassword().equals(board.getPassword())) {
            throw new BasicException(ErrorCode.FAILED_UPDATE); // 비밀번호 오류
        }
        //수정
        bo.setSubject(board.getSubject());
        bo.setContent(board.getContent());

        return bo;
    }

    /**
     * board 삭제하기
     * */
    @Override
    public void delete(Long bno, String password) {

        FreeBoard board = freeBoardRepository.findById(bno)
                .orElseThrow(()->new BasicException(ErrorCode.FAILED_DELETE));// 삭제 오류

        if(!board.getPassword().equals(password)) throw new BasicException(ErrorCode.FAILED_DELETE); // 비밀번호 오류

        freeBoardRepository.delete(board);
    }
}
