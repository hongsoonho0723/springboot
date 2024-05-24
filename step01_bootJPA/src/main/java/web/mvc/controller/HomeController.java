package web.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {



    @RequestMapping("/")
    public ModelAndView index(){
        log.info("index왔어요~");


        return new ModelAndView("index", "message", "쉬고싶다");

    }


}
