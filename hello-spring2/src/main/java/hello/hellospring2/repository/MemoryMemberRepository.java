package hello.hellospring2.repository;

import hello.hellospring2.domian.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();  // 동시성 문제가 있으나 예제로 이렇게 처리함
    private static long sequence = 0L;  // 마찬가지

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  // member의 name은 set 됬다는 가정
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 id 값 줘서 저장된 데이터를 갖고오면 되는데 없을 경우 null처리
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식 으로 처리
        // filter 멤버에서 멤버이름이 파라미터로 넘어온거랑 같으냐 비교해서 같은것만 필터링. 같은것만 반환되는데
        // findAny(); 은 하나찾으면 그걸 반환.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store.values()들이 member들.
        // 리스트로 반환. 끝
        return new ArrayList<>(store.values());
    }
}
