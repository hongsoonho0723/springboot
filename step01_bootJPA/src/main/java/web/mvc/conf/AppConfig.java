package web.mvc.conf;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {

    @PersistenceContext //EntityManger를 주입 받는다
    private EntityManager entityManager; //각 서비스마다 매번 새로운 EntityManager(객체)가 생성되서 들어온다 공유x

    @Bean //bean = class 를 등록할떄 선언
    public JPAQueryFactory getQueryFactory() {
      log.info("getQueryFactory 호출됨........");
      log.info("entityManager = {}", entityManager);
      JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
      return jpaQueryFactory;

    }


}
