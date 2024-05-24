package web.mvc.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.mvc.domain.Reply;
import web.mvc.dto.FreeBoardDTO;
import web.mvc.repository.ReplyRepository;

import java.util.ArrayList;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    /** 댓글 등록
     *  save == persist 객체를 넣었지만 fk를 참조하는 데이터 베이스가 만들어 진다
     * */
    @Override
    public void insert(Reply reply) {
        replyRepository.save(reply);
    }

    /** 댓글 삭제
     *
     * */
    @Override
    public void delete(Long id) {
        replyRepository.deleteById(id);
    }
}
