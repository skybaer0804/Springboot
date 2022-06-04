package hello.hellospring2.service;

import hello.hellospring2.domian.Member;
import hello.hellospring2.repository.MemberRepository;
import hello.hellospring2.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    // 생성자
    // memoryRepository를 외부에서 받아주는 걸로 바꾼다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원은 가입이 안된다.

        // 리펙토링!
        // 과거에는 if null 조건으로 체크했지만 현재는 Optional로 감싸져서 null 체크는 이미 처리되었다.
        // 그래서 .isPresent 사용할 수 가 있게 됬고, 존재하면 바로 함수 실행하는 식으로 간다.
        // 추가로 .get() 은 권장하지 않고, orElseGet()을 많이 쓴다. = 값이 있으면 꺼내고 없으면 단거를 꺼낸다.
/*
        Optional<Member> result = memberRepository.findByName(member.getName()); // ctrl + alt + V;
        result.ifPresent(m -> { // 찾아봤는데 m (member)에 이미 값이 있으면!
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
*/

        // 추가로 Optional<Member>로 받으려니 코드 가독성이 별로다.
        // 아예 result 변수로 받지 말고 바로 .isPresent를 쓰면 된다.
/*
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
*/

        // 추가로 위 메서드로직이 형성된것들은 메소드화 시키는게 좋다.
        // 드래그 상태로 Shft + Ctrl + Alt + T => Extract Method

        vaildateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member); // 회원가입
        return member.getId(); // 임의로 id 반환
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
