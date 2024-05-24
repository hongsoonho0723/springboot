package web.mvc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")

@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public String login(){
      log.info("login호출");
        return "user/login";
    }



}
