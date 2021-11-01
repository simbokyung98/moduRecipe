package gp.web;

import gp.service.BuyService;
import gp.web.dto.CartDto;
import gp.web.dto.MemberDto;
import gp.web.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;
    private final HttpSession session;
    //장바구니 페이지
    @GetMapping("/cart")
    public String cart(Model model){

        MemberDto user = (MemberDto)session.getAttribute("user");

        if(user == null){
            return "redirect:/login";
        }
        List<CartDto> cartDtoList = buyService.getCart(user.getUsername());

        model.addAttribute("cartList", cartDtoList);

        return "/basket";
    }

    //장바구니 담기
    @PostMapping("/saveCart")
    public ModelAndView saveCart(CartDto cartDto, ModelAndView mav){
        MemberDto user = (MemberDto)session.getAttribute("user");

        if(user != null){

            String cm = buyService.findCart(cartDto.getCartMaterial());
            if (cm != null) {
                buyService.cartupdate(cartDto.getCartMaterial(), cartDto);
            }else{
                buyService.saveCart(cartDto);
            }
            mav.clear();
            mav.addObject("data", new Message("장바구니에 넣었습니다", "/meterialMain"));
            mav.setViewName("Message");
            return mav;

        }else{
            mav.clear();
            mav.addObject("data", new Message("로그인이 필요한 기능입니다", "/login"));
            mav.setViewName("Message");
            return mav;
        }

    }
    //장바구니 담기
    @PostMapping("/saveCartList")
    public ModelAndView saveCartList(@RequestBody List<CartDto> cartDtoList, ModelAndView mav){

        System.out.println(cartDtoList.size());
        MemberDto user = (MemberDto)session.getAttribute("user");

        if(user != null){

            for (CartDto cartDto : cartDtoList) {
                System.out.println("cartDto = " + cartDto);
                cartDto.setUsername(user.getUsername());
                String cm = buyService.findCart(cartDto.getCartMaterial());
                if (cm != null) {
                    buyService.cartupdate(cartDto.getCartMaterial(), cartDto);
                }else{
                    buyService.saveCart(cartDto);
                }
            }
            mav.clear();
            mav.addObject("data", new Message("장바구니에 넣었습니다", "/meterialMain"));
            mav.setViewName("Message");
            return mav;

        }else{
            mav.clear();
            mav.addObject("data", new Message("로그인이 필요한 기능입니다", "/login"));
            mav.setViewName("Message");
            return mav;
        }

    }

    //선택 상품 삭제
    @RequestMapping("/cartdelete/{cartKey}")
    public String cartDelete(@PathVariable("cartKey") Long cartKey){
        buyService.cartDelete(cartKey);

        return "redirect:/cart";
    }

    //선택 상품 수량 UPDOWN 업데이트
    @GetMapping("/cartUpDown/{cartKey}/{updown}")
    public String cartUpDown(@PathVariable("cartKey") Long cartKey, @PathVariable("updown") int updown){
        if(updown == 1){
            buyService.cartDown(cartKey);
        }else if(updown == 2){
            buyService.cartUp(cartKey);
        }
        return "redirect:/cart";
    }


}

