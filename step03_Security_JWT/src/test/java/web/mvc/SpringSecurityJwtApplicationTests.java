package web.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Member;
import web.mvc.repository.MemberRepository;

@SpringBootTest
@Transactional
@Commit


class SpringSecurityJwtApplicationTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 관리자 등록
     */
    @Test
    void memberInsert() {
        String encPwd = passwordEncoder.encode("1234");

        memberRepository.save(Member.builder().id("admin").pwd(encPwd).role(
                "ROLE_ADMIN").address("오리역").name("장희정").build());
    }
}
