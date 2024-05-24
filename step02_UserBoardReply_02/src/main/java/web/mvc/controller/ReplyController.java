package web.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.mvc.domain.FreeBoard;
import web.mvc.domain.Reply;
import web.mvc.service.FreeBoardService;
import web.mvc.service.ReplyService;

@Controller
@Slf4j
@RequestMapping("/reply") // 요청 url이 /reply/~ 이런 형식은 여기서 처리하겠다
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private FreeBoardService freeBoardService;

    /** 작성하기로 이동
     *  뷰에서 hidden으로 되어 있는 board.bno를 가져와서
     *  reply의 write.jsp로 bno를 포함하여 이동합니다
     */
    @PostMapping("/writeForm")
    public ModelAndView writeForm(String bno){
        log.info("reply updateForm bno:"+bno);

        return new ModelAndView("/reply/write", "bno", bno);
    }


    /** 작성하기
     *  bno에 해당하는 board에 게시글을 등록합니다
     */
    @PostMapping("/insert")
    public String insertForm(Long bno, Reply reply ) {
        log.info("reply insertForm bno:{},content:{}",bno,reply.getContent());

        reply.setFreeBoard(new FreeBoard(bno));

        replyService.insert(reply);

        return "redirect:/board/read/"+bno+"?flag";
    }

    /** 삭제하기
     *  bno에 해당하는 board의 reply를 삭제 합니다
     * */
    @GetMapping("/delete/{rno}/{bno}")
    public String delete(@PathVariable String rno, @PathVariable String bno){

        replyService.delete(Long.parseLong(rno));

        return "redirect:/board/read/"+bno;
    }
}
