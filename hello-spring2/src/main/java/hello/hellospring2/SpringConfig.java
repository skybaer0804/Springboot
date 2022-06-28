package hello.hellospring2;


import hello.hellospring2.repository.JpaMemberRepository;
import hello.hellospring2.repository.MemberRepository;
import hello.hellospring2.repository.MemoryMemberRepository;
import hello.hellospring2.repository.JdbcTemplateMemberRepository;
import hello.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;

//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;  // jpa EntityManger 주입
//
//    @Autowired
//    public SpringConfig(EntityManager em){  // 생성자.
//        this.em = em;
//    }
//
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 이렇게 해 놓으면  끝.
    // 아까 만든 스프링데이터JPA 인터페이스에 JpaRepository를 extends 해놓으면
    // 자동으로 스프링 빈에 등록이 되어있어서 그저 가져다 쓰면된다.


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new jdbcMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
