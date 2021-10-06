package gp.web;

import gp.service.BuyService;
import gp.web.dto.CartDto;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;
    private final HttpSession session;

    @PostMapping("/saveCart")
    public String saveCart(CartDto cartDto, ModelAndView mav){
        MemberDto user = (MemberDto)session.getAttribute("user");

        if(user != null){

        }else{
           mav.addObject("로그인이 필요한 기능입니다", "/login");
           mav.setViewName("meter_detail");
        }

    return null;

    }

}
