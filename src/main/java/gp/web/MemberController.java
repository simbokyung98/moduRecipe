package gp.web;

import gp.domain.Member;
import gp.service.AdminService;
import gp.service.MemberService;
import gp.web.dto.AdminDto;
import gp.web.dto.MemberDto;
import javassist.bytecode.annotation.MemberValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AdminService adminService;
    private final HttpSession session;





    // 회원가입
    @GetMapping("/user/join")
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
            return "redirect:/" ;

        }
        else{
            return "redirect:/login";
        }


    }

    // 사용자 로그아웃
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
            return "membercheck";
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
        return "redirect:/datachange";

    }

    @GetMapping("/datachange")
    public String updateform(Model model, HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        } else {
            return "/datachange";
        }

    }


    //---------------------------------------------------------


    /*
    // 아이디 중복 확인
    @ResponseBody
    @RequestMapping(value="/idCheck", method= RequestMethod.POST)
    public String idCheck(Member member){
        //select * from member where userid = #{};
        //이 member 객체에는 id만 값이 들어있고, 다른 것은 다 null 값이다.
        Member m = memberService.idCheck(member);
        String message=null;
        if(m==null) {//사용할 수 있다. db에서 찾았는데없으니까
            message = "success";
        }else {//사용할 수 없다.
            message ="fail";
        }
        return message;
    }





    @GetMapping("/user-username/{username}/exists")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        return ResponseEntity.ok(memberService.checkUsername(username));
    }
*/



    // 아이디 찾기
    @GetMapping("/idfind")
    public String idfind() {
        return "/idFind";
    }

    // 비밀번호 찾기
    @GetMapping("/pwdfind")
    public String pwdFind() {
        return "/pwdFind";
    }

}
