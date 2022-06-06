package hello.hellospring2.controller;


import hello.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();
    // new 해서 새로운 개체를 만들어 쓰지 않는다. 하나만 생성해서 공용으로 쓰는게 좋다.
    // 이제 스프링 컨테이너에서 관리되고 있는 빈을 갖고 와서 쓴다. 스프링 컨테이너는 딱 하나만 등록된다.
    //

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // @Controller 애너테이션을 설정하면,
    // 스프링이 스프링컨테이너에 MemberController 객체를 하나 생성해서 컨테이너 안에서 관리한다.
    // 스프링 컨테이너 안에 있는 memberService 빈즈를 쓰기 위해서
    // 생성자로 만들고 외부에서 memberService를 받아서 쓸수 있게끔 만든다.
    // @Autowired 에너테이션을 넣어주면 스프링이 스프링컨테이너 안에 있는 memberService를 찾아다가 딱 연결을 시켜준다.

}
