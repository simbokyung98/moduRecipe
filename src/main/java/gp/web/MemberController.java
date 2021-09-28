package gp.web;

import gp.domain.Member;
import gp.service.MemberService;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final HttpServletRequest request;
    private final HttpSession session;


    @GetMapping("/")
    public String main() {
        return "/index";
    }

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "/memberJoin";
    }

    // 회원가입 처리
    @PostMapping("/userJoin")
    public String Sign(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/login";
    }

    // 로그인
    @GetMapping("/login")
    public String login() {
        return "/memberLogin";
    }

    @PostMapping("/userLogin")
    public String userLogin(MemberDto memberDto){
        MemberDto result = memberService.userLogin(memberDto);

        if(result != null){

            session.setAttribute("user", result);
            return "redirect:/" ;

        }else{
            return "redirect:/login";
        }
    }

}

