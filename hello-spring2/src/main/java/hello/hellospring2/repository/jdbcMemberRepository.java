package hello.hellospring2.repository;

import hello.hellospring2.domian.Member;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class jdbcMemberRepository implements MemberRepository{

    //spring에서 properties에 설정한 jdbc data를 dataSource로 갖고있음.
    private final DataSource dataSource;

    // 그래서 jdbcMemberRepositroy 생성자로 주입
    public jdbcMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }


    // 이후는 작성하지 않았다. 진행하고자하면 MemoryMemberRepository 코드들 복사 및 수정진행
    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
