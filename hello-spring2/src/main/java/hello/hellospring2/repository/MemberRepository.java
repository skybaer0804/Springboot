package hello.hellospring2.repository;

import hello.hellospring2.domian.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  // 저장
    Optional<Member> findById(Long id);  // id 조회
    Optional<Member> findByName(String name);   // 이름 조회
    List<Member> findAll();  // 등록된 모든 회원 조회

}
