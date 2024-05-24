package web.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String index(){
        log.info("index 호출");
        return "index";
    }
/*

    @RequestMapping("/")
    public ModelAndView index(){
        log.info("index왔어요~");


        return new ModelAndView("index", "message", "쉬고싶다");

    }
*/


}
