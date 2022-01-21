package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 컴포넌트 스캔 방식
//@Controller -> @Component이 등록 되어있다.
@Controller
public class MemberController {

    // DI
    // 1. 생성자 주입
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService; // SpringConfig에서 등록한 멤버서비스를 넣는다.
    }

    // 2. 직접 주입
    //@ Autowired private final MemberService memberService;

    // 3. setter 주입
    /*
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    */
}
