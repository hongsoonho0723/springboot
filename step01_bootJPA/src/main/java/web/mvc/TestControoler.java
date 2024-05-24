package web.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControoler {

    @RequestMapping("/test")
    public String aa(){
        System.out.println("여기왔나");

        return "ok";
    }


}
