package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    // Get: url에서 접속, 보통 조회할 때
    // Post : form에서 전달받을 때

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); // member의 리스트를 전부 반환
        model.addAttribute("members", members); // members는 모든회원을 조회해서 담아둔 형태임
        return "members/memberList";
    }
}
