package gp.web;

import gp.service.AdminService;
import gp.service.MemberService;
import gp.web.dto.MemberDto;
import gp.web.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MemberService memberService;
    private final HttpSession session;






    // 회원 정보
    @GetMapping("/adminUser")
    public String memberlist(Model model) {
        List<MemberDto> memberDtoList = adminService.getMember();
        model.addAttribute("adminuserlist", memberDtoList);
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "회원");
        return "/adminUser";
    }


    // 관리자 회원 삭제
    @RequestMapping("/memberDelete/{userid}")
    public String memberDelete(@PathVariable("userid") Long id) {
        adminService.adminUserDelete(id);

        return "redirect:/adminUser";
    }




/*
    @GetMapping("/member/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {

        List<MemberDto> memberDtoList = adminService.searchPosts(keyword);
        model.addAttribute("adminList", memberDtoList);

        return "/member/adminUser.html";
    }

 */




    @GetMapping("/adminMeter")
    public String asdasd(){
        return "adminMeter";
    }

}
