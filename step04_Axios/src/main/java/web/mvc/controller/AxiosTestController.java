package web.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.mvc.dto.UserReq;
import web.mvc.exception.MemberAuthenticationException;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class AxiosTestController {
    
    @GetMapping("/a")
    public String test(){
        log.info("여기오니");
        return "ok";
    }
    
    /**
     * 등록하기
     * */
    @PostMapping("/user")
    public String insert(@RequestBody UserReq userReq){ // axios는 json의 형태로 데이터를 전달하기때문에 @RequestBody가 없으면 데이터를 받을 수없다
        System.out.println("userReq = " + userReq);
        return "ok";
    }

    /**
     * 삭제하기
     * */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){ //@PathVariable을 사용해 넘어오는 {id}값을 가져온다
        System.out.println("id = " + id);
        if(id !=5){
            throw new MemberAuthenticationException("아이디값 유효하지 않아요" ,"ID Bad", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }



    /**
     * 수정하기
     * */
    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@RequestBody UserReq userReq ,@PathVariable Integer id){
        System.out.println("userReq = " + userReq + " | id = "+id);
        return ResponseEntity.status(HttpStatus.OK).body("1");

    }


    /**
     * 부분조회
     * */
    @GetMapping("/user/{id}")
    public ResponseEntity<?> selectById(@PathVariable String id){
        System.out.println("id = " + id);
        UserReq userReq = new UserReq("chan","chan@gmail.com",20);
        return ResponseEntity.status(HttpStatus.OK).body(userReq);

    }

    /**
     * 전체조회 list
     * */
    @GetMapping("/users/")
    public ResponseEntity<?> selectAll(){

        List<UserReq> list = new ArrayList<>();
        list.add(new UserReq("chan","chan@gmail.com",20));
        list.add(new UserReq("hong","chan@gmail.com",10));
        list.add(new UserReq("soon","chan@gmail.com",30));
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

}
