package web.mvc.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {

    @PersistenceContext
    private EntityManager entityManager;


    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        log.info("getQueryFactory 호출....");

        return new JPAQueryFactory(entityManager);

    }



}
