package web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    /**
     *  index.jsp로 가기 위한 기능
     *  매핑 주소에 아무것도 없으면 http://localhost:9000 -> 이것만 보내면 된다. "/" 생략
     * */
    @GetMapping("/")
    public String home()
    {
        System.out.println("오니?");
        return "index";
    }
}
