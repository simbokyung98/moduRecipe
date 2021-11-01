package gp.web;

import gp.domain.Member;
import gp.service.AdminService;
import gp.service.MemberService;
import gp.web.dto.MemberDto;
import gp.web.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String memberlist(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Member> memberDtoList = adminService.pageGetAllMember(pageable);
        model.addAttribute("adminuserlist", memberDtoList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("check", adminService.getListCheck(pageable));
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




    //관리자 회원 정보 검색
    @GetMapping("/memberlistsearch")
    public String adminMemberSearch(@RequestParam(value="keyword") String keyword, @RequestParam(value="searchType") String searchType, Model model,
                                    @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Member> memberDtoList = adminService.pageGetMemberSearch(keyword, searchType, pageable);
        model.addAttribute("adminuserlist", memberDtoList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", adminService.getListCheck(pageable));
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "회원");
        return "adminUser";

    }






}
