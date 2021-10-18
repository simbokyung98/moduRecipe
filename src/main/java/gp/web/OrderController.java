package gp.web;

import gp.domain.Material;
import gp.domain.Order;
import gp.service.MaterialService;
import gp.service.OrderService;
import gp.web.dto.MaterialDto;
import gp.web.dto.MemberDto;
import gp.web.dto.OrderDetatilDto;
import gp.web.dto.OrderDto;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MaterialService materialService;


    @GetMapping("/order")
    public String orderform(HttpSession session, OrderDto orderDto, OrderDetatilDto orderDetatilDto, Model model){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        System.out.println("loginMember : " + loginMember.toString());
        return "order";
    }

    @PostMapping("/material/order")
    public String materialForm(HttpSession session, int hiddenTotalPrice, @RequestParam("keys") String materialKeys, Model model){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");

        model.addAttribute("materialKeys",materialKeys);
        model.addAttribute("totalPrice",hiddenTotalPrice);
        model.addAttribute("userName",loginMember.getName());

//        String[] keys = materialKeys.split(",");

        return "order";
    }

    @PostMapping("/material/orderProcess")
    public String orderProcess(HttpSession session, OrderDto orderDto, OrderDetatilDto orderDetatilDto, @RequestParam("keys") String materialKeys, Model model) throws Exception{

        MemberDto loginMember=(MemberDto)session.getAttribute("user");

        Date date = new Date();

        orderDto.setOrderdate(date);
        orderDto.setOrderstate(1);
        orderDto.setMemberid(loginMember.getId());

        System.out.println("orderDto : " + orderDto.toString());
        System.out.println("orderDetatilDto : " + orderDetatilDto.toString());

        model.addAttribute("materialKeys",materialKeys);
        model.addAttribute("userName",loginMember.getName());

        Long orderKey  = orderService.save(orderDto);

        Order order = new Order();
        order.setOrderkey(orderKey);
        orderDetatilDto.setOrder(order);

        String[] materialList = materialKeys.split(",");
        List<Material> materialDtoList = materialService.getMaterialById(materialList);

        for(int i = 0; i < materialDtoList.size(); i++){
            System.out.println("materialDtoList.get(i) : " + materialDtoList.get(i).toString());
            orderDetatilDto.setMaterial(materialDtoList.get(i));
            orderService.saveOrderDetail(orderDetatilDto);
        }

        return "orderComp";
    }

    @GetMapping("/orderlist")
    public String orderList(HttpSession session, Model model){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        Long userId = loginMember.getId();

        List<Map<String, Object>> orderList = orderService.getOrderListById(userId);

        model.addAttribute("orderList", orderList);

        return "orderlist";
    }

}
