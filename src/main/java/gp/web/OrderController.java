package gp.web;

import gp.domain.Order;
import gp.service.OrderService;
import gp.web.dto.MemberDto;
import gp.web.dto.OrderDetatilDto;
import gp.web.dto.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/order")
    public String orderform(HttpSession session, OrderDto orderDto, OrderDetatilDto orderDetatilDto, Model model){

        return "order";
    }

}
