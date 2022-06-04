package hello.hellospring2.repository;


import hello.hellospring2.domian.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// public 삭제. 다른데서 갖다 쓸게 아니기 때문임.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();  //MemoryMemberRepository 로 받게끔 바꿔주자.

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test // org.JUnit.Jupiter.api
    public void save() {
        Member member = new Member();
        member.setName("Spring");   //shift + enter;

        repository.save(member);  //test

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));  글자로 잘 보진 않는다.
        //Assertions.assertEquals(member, null);   // save() 함수 오류남
//        Assertions.assertEquals(member, result);   //org.JUnit.Jupiter.api

        assertThat(member).isEqualTo(result);  // org.assertj.core.api
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        //test
        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void finAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
