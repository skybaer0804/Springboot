package hello.hellospring2.domian;

import javax.persistence.*;

@Entity   // 이제부터 jpa가 관리하는 entity라는 뜻.
public class Member {

    // DB Insert하면 숫자 자동증가하는 것들은 IDENTITY라고함. 아래와 같이 애노테이션 잡아준다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이렇게 @Column 애노테이션을 통해 DB 컬럼과 변수를 매칭시켜준다.
    // 그렇게 하면 이제 변수만으로 이것저것 할 수 있게된다.
    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
