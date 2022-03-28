package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberSerivce memberSerivce;

    @Autowired
    public MemberController(MemberSerivce memberSerivce) {
        this.memberSerivce = memberSerivce;
    }

    @GetMapping("/members/new") //요청
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //post 방식으로 전달 FORM에서 post로 보냈기 때문에 이걸 사용한다.
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberSerivce.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberSerivce.findMember();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
