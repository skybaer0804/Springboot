package hello.hellospring2.service;

import hello.hellospring2.domian.Member;
import hello.hellospring2.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 기존 테스트에서는 메모리 개체를 직접 생성(생성자로 주입) 해서진행했으나
    // 스프링부트 테스트는 통합테스트이므로 스프링컨테이너에 올라와있는 개체(빈)을 사용해서 쓴다.
    // 그래서 @Autowired 애노테이션으로 사용하였으며
    // 테스트는 말그대로 테스트이다. 서비스배포가 아니다.
    // 보통 테스트용 DB서버로 하거나 아니면 로컬에서 진행한다.
    // @Transactional 은 기가 막힌 스프링 기능으로
    // 테스트를 반복할 수 있게해준다. DB의 트랜잭션 개념을 활용한걸로 commit 전 DB반영하기 전에 ROOLBACK 하면
    // DB에 데이터가 반영이 안되니까
    // 테스트를 계속 할 수 잇다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        //given : 주어지는 것
        Member member = new Member();
        member.setName("hello");

        //when : 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과(검증)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }
}