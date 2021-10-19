package gp.web;

import gp.service.AdminService;
import gp.domain.Member;
import gp.domain.MemberRepository;
import gp.service.MemberService;
import gp.web.dto.AdminDto;
import gp.web.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    private final AdminService adminService;
    private final HttpServletRequest request;
    private final HttpSession session;
    private final MemberRepository memberRepository;

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

    // 사용자 로그인
    @GetMapping("/login")
    public String login() {
        return "/memberLogin";
    }


    // 사용자 로그인 처리
    @PostMapping("/userLogin")
    public String userLogin(MemberDto memberDto) {

        MemberDto result = memberService.userLogin(memberDto);

        if(result != null){

            session.setAttribute("user", result);
            session.setAttribute("username",memberDto.getUsername());

            return "redirect:/" ;

        }else{
            return "redirect:/login";
        }
    }
    //로그아웃
    @GetMapping("/userlogout")
    public String logout(){
        session.setAttribute("user", null);
        return "index";
    }

    // 관리자 로그인
    @GetMapping("/adLogin")
    public String adLog() {
        return "/adminLogin";
    }

    // 관리자 로그인 처리
    @PostMapping("/adminLog")
    public String adminLogin(AdminDto adminDto) {

        AdminDto adResult = adminService.adminLogin(adminDto);

        if(adResult != null){

            session.setAttribute("admin", adResult);
            return "redirect:/" ;

        }
        else{
            return "redirect:/adLogin";
        }

    }

    // 관리자 로그아웃
    @GetMapping("/adminlogout")
    public String adlogout(){
        session.setAttribute("admin", null);
        return "index";
    }




    //------------------------------------------------------------




    //회원정보확인
    @GetMapping("/membercheck")
    public String memberchech(Model model, HttpSession httpSession){
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        } else {

            return "/membercheck";
        }
    }
    @PostMapping("/membercheck")
    public String pwcheck(MemberDto memberDto, HttpSession session, Model model, RedirectAttributes attr){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        memberDto.setUsername(loginMember.getUsername());
        MemberDto memberXO = memberService.userLogin(memberDto);
        if(memberXO == null){
            attr.addFlashAttribute("msg","비밀번호가 잘못되었습니다");
            return "redirect:/membercheck";
        }
        session.setAttribute("user", memberXO);

        System.out.println("memberXO + " + memberXO.toString());
        return "datachange";

    }

    @PostMapping("/datachange/{id}")
    public String pwChange(MemberDto memberDto, HttpSession session, Model model, RedirectAttributes attr, @PathVariable("id") Long id){

        memberService.userUpdatePwd(id, memberDto.getPassword());

        return "/memberLogin";

    }

    @DeleteMapping("/member/{id}")
    public String deleteUser(@PathVariable("id") Long id){

        memberService.deleteUser(id);

        return "/memberLogin";

    }


}

