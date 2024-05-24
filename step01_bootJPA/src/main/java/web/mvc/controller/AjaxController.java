package web.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.mvc.dto.UserReqDTO;
import web.mvc.dto.UserResDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class AjaxController {

    @GetMapping("/{id}")
    public UserResDTO getUser(@PathVariable String id ) throws IllegalAccessException {
        log.info("id = {} ", id);

        if(!"jang".equals(id)){
            throw new IllegalAccessException("아이디 중복입니다");
        }

        return UserResDTO.builder().userId(id).userAge(10).userAddr("오리역").build();


    }

    @GetMapping
    public ResponseEntity<?> all(){
    log.info("전체 조회");
    List<UserResDTO> list = new ArrayList<>();
    list.add(UserResDTO.builder().userId("soon").userAge(10).userAddr("오리역1").build());
    list.add(UserResDTO.builder().userId("hee").userAge(20).userAddr("오리역2").build());
    list.add(UserResDTO.builder().userId("hong").userAge(30).userAddr("오리역3").build());
    return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody UserReqDTO userReqDTO){
    log.info("users 요청 : userReqDTO = {}", userReqDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body("OK1");
    }

    @PostMapping("/test")
    public ResponseEntity<?> insert2( UserReqDTO userReqDTO){
    log.info("users 요청 : userReqDTO = {}", userReqDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body("OK2");
    }


}
