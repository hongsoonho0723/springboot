package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.mvc.domain.Member;
import web.mvc.service.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    /*
    * 가입하기
    * */
    @PostMapping("/members")
    public ResponseEntity<?> signUp(@RequestBody Member member) { //json전달
        log.info("member = {}", member);
        memberService.signUp(member);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }


   /* @PostMapping("/members")
    public String addMember(Member member) {

    return "addMember create";
    }*/

}
