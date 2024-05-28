package web.mvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Member;
import web.mvc.exception.MemberAuthenticationException;
import web.mvc.repository.MemberRepository;

@Transactional
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {



    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder pe;


    @Override
    public String duplicateCheck(String id) {
        return "";
    }




    @Override
    public void signUp(Member member) {
        if(memberRepository.existsById(member.getId())){
            throw new MemberAuthenticationException("아이디 중복입니다","Duplicate ID");
        }

        //비밀번호 암호화
        String encPwd = pe.encode(member.getPwd());
        member.setPwd(encPwd);

        //ROLE설정
        member.setRole("ROLE_USER");

        Member savedMember = memberRepository.save(member);
        log.info("savedMember = {}", savedMember);
    }

    /*
    @Transactional
    public void signUp(Member member) {

        String ecoderedPwd = pe.encode(member.getPwd());
        member.setPwd(ecoderedPwd);
        // 회원 정보 저장
        memberRepository.save(member);

       // member.setPwd(ecoderedPwd);
        member.setRole("ROLE_USER");
        memberRepository.save(member);

    }*/
}
