package web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.mvc.domain.FreeBoard;
import web.mvc.service.FreeBoardService;

import java.util.List;

@RequestMapping("/board")
@Controller
public class FreeBoardController {

    @Autowired
    private FreeBoardService freeBoardService;



    @GetMapping("/list")
    public String list(Model model) {

        List<FreeBoard> list = freeBoardService.selectAll();
        model.addAttribute("freeList", list);


        return "board/list";
    }
/*

    @GetMapping
    public ResponseEntity<?> all(){
        log.info("전체 조회");
        List<UserResDTO> list = new ArrayList<>();
        list.add(UserResDTO.builder().userId("soon").userAge(10).userAddr("오리역1").build());
        list.add(UserResDTO.builder().userId("hee").userAge(20).userAddr("오리역2").build());
        list.add(UserResDTO.builder().userId("hong").userAge(30).userAddr("오리역3").build());
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }
*/



}
