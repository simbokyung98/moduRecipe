package gp.web;

import gp.service.MemberService;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @GetMapping("/user/join")
    public String join() {
        return "/memberJoin";
    }

    // 회원가입 처리
    @PostMapping("/userJoin")
    public String Sign(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    // 로그인
    @GetMapping("/user/login")
    public String login() {
        return "/memberLogin";
    }
}
