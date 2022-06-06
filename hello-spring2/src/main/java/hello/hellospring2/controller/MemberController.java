package hello.hellospring2.controller;

import hello.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

//    DI 필드주입 방석, 비추천 -> 나중에 로딩 전에 설정을 다른무언가로 바꾸기 쉽지 않음.
//    @Autowired private final MemberService memberService;

    private final MemberService memberService;


//    DI setter 방식, 비추천 -> 노출이 되서 로딩 이후에도 바꿀 수가 있음.
//    @Autowired
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
