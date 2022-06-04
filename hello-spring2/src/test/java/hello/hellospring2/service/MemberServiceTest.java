package hello.hellospring2.service;

import hello.hellospring2.domian.Member;
import hello.hellospring2.repository.MemberRepository;
import hello.hellospring2.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memoryRepository = new MemoryMemberRepository();

    // memberService 안에서 만들어지는 memoryRepository랑 test 서버에서의 memoryRepository가 다른 개체이다.
    // memberService에서 memoryRepository를 외부에서 받는걸로 바꾼다.

    MemoryMemberRepository memoryRepository;
    MemberService memberService;

    @BeforeEach // 동작하기 전 실행
    public void beforeEach(){
        memoryRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryRepository);
    }

    // 이렇게 하면 테스트 실행할때마다 각각 실행되면서 테스트가 독립적이되며 같은 memoryMeberRepository를 쓴다.
    // 이런것을 DI 라고 한다.

    @AfterEach
    public void afterEach(){
        memoryRepository.clearStore();
    }

    @Test
    void join() {
        // 추천 문법
        //given : 주어지는 것
        Member member = new Member();
        member.setName("hello");

        //when : 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과(검증)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    // 테스트는 완성됬으나 사실 테스트는 정상도 중요하지만 예외플로우가 더 중요하다.

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


/*
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1231");
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}