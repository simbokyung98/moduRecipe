package gp.web;

import gp.domain.Material;
import gp.domain.Order;
import gp.service.MaterialService;
import gp.service.OrderService;
import gp.web.dto.MemberDto;
import gp.web.dto.OrderDetatilDto;
import gp.web.dto.OrderDto;
import gp.web.dto.OrderListDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MaterialService materialService;


    @PostMapping("/order")
    public ResponseEntity orderform(HttpSession session, OrderDto orderDto, @RequestBody List< OrderDetatilDto> OrderDetatilDtoList, @RequestParam(value = "sum") String sum) {

//        if(session.getAttribute("user")==null){
//            return "redirect:/login";
//        }
        System.out.println();
        System.out.println("orderDetailListDto = " + OrderDetatilDtoList);
        session.setAttribute("orderDetailList", OrderDetatilDtoList);
        session.setAttribute("sum", sum);
        System.out.println(OrderDetatilDtoList.size());

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/material/order")
    public String materialForm(HttpSession session, int hiddenTotalPrice, @RequestParam("keys") String materialKeys, Model model){

        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }
        MemberDto loginMember=(MemberDto)session.getAttribute("user");

        model.addAttribute("materialKeys",materialKeys);
        model.addAttribute("totalPrice",hiddenTotalPrice);
        model.addAttribute("userName",loginMember.getName());

        session.setAttribute("sum", hiddenTotalPrice);

        return "order";
    }

    @GetMapping("/go_order")
    public String goOrder(HttpSession session, Model model){
        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        model.addAttribute("userName",loginMember.getName());
        return "order";
    }

    @PostMapping("/material/orderProcess")
    public String orderProcess(HttpSession session, OrderDto orderDto, OrderDetatilDto orderDetatilDto, @RequestParam("keys") String materialKeys, Model model) throws Exception{

        MemberDto loginMember=(MemberDto)session.getAttribute("user");

        Date date = new Date();

        orderDto.setOrderdate(date);
        orderDto.setOrderstate(1);
        orderDto.setMemberid(loginMember.getId());
        orderDetatilDto.setOrderdate(date);

        System.out.println("orderDto : " + orderDto.toString());
        System.out.println("orderDetatilDto : " + orderDetatilDto.toString());

        model.addAttribute("materialKeys",materialKeys);
        model.addAttribute("userName",loginMember.getName());

        Long orderKey  = orderService.save(orderDto);

        Order order = new Order();
        order.setOrderkey(orderKey);
        orderDetatilDto.setOrder(order);

        if(orderDetatilDto.getOrdernum()==0){
            orderDetatilDto.setOrdernum(1);
        }



        if (materialKeys.isEmpty() == false) {
            String[] materialList = materialKeys.split(",");
            List<Material> materialDtoList = materialService.getMaterialById(materialList);

            for(int i = 0; i < materialDtoList.size(); i++){
                System.out.println("materialDtoList.get(i) : " + materialDtoList.get(i).toString());
                orderDetatilDto.setMaterial(materialDtoList.get(i));
                orderService.saveOrderDetail(orderDetatilDto);
            }
            session.removeAttribute("sum");

        } else {
            List< OrderDetatilDto> orderDetatilDtoList = (List<OrderDetatilDto>) session.getAttribute("orderDetailList");
            String[] materialList1 = new String[orderDetatilDtoList.size()];

            int index = 0;
            for(OrderDetatilDto orderDetatilDto1 : orderDetatilDtoList){
                materialList1[index] = orderDetatilDto1.getMaterialname();
                index++;
            }
            List<Material> materialDtoList1 = materialService.getMaterialByTitles(materialList1);

            index = 0;
            for(OrderDetatilDto orderDetatilDto1 : orderDetatilDtoList){
                orderDetatilDto1.setMaterial(materialDtoList1.get(index));
                orderDetatilDto1.setOrder(order);
                orderService.saveOrderDetail(orderDetatilDto1);
                index++;
            }
            session.removeAttribute("orderDetailList");
            session.removeAttribute("sum");
        }



        return "orderComp";
    }

    @GetMapping("/orderlist")
    public String orderList(HttpSession session, Model model,@PageableDefault(size = 5, sort = "materialKey", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Material> materialDtoList=materialService.pageGetAllMaterial(pageable);

        model.addAttribute("material", materialDtoList);

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        Long userId = loginMember.getId();

        List<Map<String, Object>> orderList = orderService.getOrderListById(userId);

        model.addAttribute("orderList", orderList);


        return "orderlist";
    }

    @DeleteMapping("/orderlist/{id}")
    public String deleteOrderDetail(@PathVariable("id") Long id){
        orderService.deleteOrderDetail(id);

        return "/orderlist";
    }

    //관리자 주문 페이지 띄우기
    @GetMapping("/adminOrder")
    public String adminorder(Model model){

        List<OrderListDto> orderListDtoList = orderService.getOrderList();

        model.addAttribute("orderList", orderListDtoList);

        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "주문");
        return "adminOrder";
    }

}
