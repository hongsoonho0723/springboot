package web.mvc.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.mvc.domain.User;
import web.mvc.service.UserService;

@Controller
@Slf4j
@RequestMapping("/user") // 요청 url이 /user/~ 이런 형식은 여기서 처리하겠다
@RequiredArgsConstructor //final필드를 찾아서 생성자를 이용한 주입
public class UserController {
    private final UserService userService;

    /**
     *  header.jsp에 a태그 눌렀을때 단순 이동 처리 하기위해
     *  ex) /user/login -> WEB-INF 밑 views 밑 user 밑 login.jsp로 이동
     * */
    @GetMapping("/{url}")
    public void url(@PathVariable String url){
        log.info("user url:"+url);
    }

    /** 로그인 체크
     * view에서의 데이터가 userId, pwd로 온다 ->  User의 속성의 변수명과 일치하기 때문에  User객체로 데이터를 받을 수 있다
     * Session에 데이터를 저장해야 하기때문에 HttpServletRequest request사용
     * Session session = request.getSession();
     * session.setAttribute("loginUser",user) sessionScope에 "loginUser"라는 이름으로 User객체를 저장
     * 요청주소를 /user/login 하여 user의 url 메소드로 호출되고 login.jsp로 이동한다
     * */
    @PostMapping("/loginCheck")
    public String login(HttpSession session ,  User user){
        log.info("user login userID={}, pwd={}",user.getUserId(), user.getPwd());

        User dbUser = userService.loginCheck(user);
        session.setAttribute("loginUser", dbUser);

        return "index";
    }

    /** 로그아웃 기능
     *  HttpServletRequest request 를 사용하여 Session session을 얻어 온 후
     *  SesscionScope에 저장되어 있는 loginUser를 지웁니다
     *  다시 redirect 이동 방식으로 index.jsp 로 이동 합니다 -> 요청주소 때문에 HomeController에 있는 index메소드로 매핑된다
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        log.info("user logout");
        session.invalidate();//모든세션의정보를 무효화

        return "redirect:/";
    }


}
