package hello.hellospring2.repository;

import hello.hellospring2.domian.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 EntityManager로 모든게 동작된다.
    // build.gradle에서 data-jap 추가했을때 Spring boot가 자동으로 EnttiyManager를 생성해준다.
    // 그래서 그냥 아래처럼 injection 해주면 쓸수있다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }





    @Override
    public Member save(Member member) {
        // .persist는 영속하다 영구저장하다는 뜻으로
        em.persist(member);
        return member;
        // 이렇게 하면 다 끝난다.
        //jpa가 insert 쿼리 다 집어넣어주고 id set까지 다해준다.
        //

    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입이랑 식별자 넣어주면 끝.

        return Optional.ofNullable(member); // 값이 없을 수도 있기에 Optiona.ofNullable처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
        // jpql을 짜야함.
        // setParameter로 변수 name을 set 해주고
        // 조회한 데이터를 getResult를 함.
        // findAny로 하나만 찾으니 stream().findAny()를 함.
        //
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // inline 단축키
        // table을 대상으로 쿼리를 날리는게 아니라 Entity 즉, em객체에 쿼리를 날린다.
        // member m 자체에서 검색한다.
    }
}
