package gp.service;

import gp.domain.*;
import gp.web.dto.OrderDetailListDto;
import gp.web.dto.OrderDetatilDto;
import gp.web.dto.OrderDto;
import gp.web.dto.OrderListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderDetailRepository orderDetailRepository;
    @Autowired private MemberRepository memberRepository;

    public Long saveOrder(OrderDto orderDto){
        return orderRepository.save(orderDto.toEntity()).getOrderkey();
    }
    public Long saveOrderDetail(OrderDetatilDto orderDetatilDto){
        return orderDetailRepository.save(orderDetatilDto.toEntity()).getOrderdetailkey();
    }

    public List<OrderDto> getAllOrder(){
        List<Order> orderList= orderRepository.findAll();
        List<OrderDto> orderDtoList=new ArrayList<>();

        for(Order order : orderList){
            OrderDto orderDto = OrderDto.builder()
                    .orderkey(order.getOrderkey())
                    .memberid(order.getMemberid())
                    .orderdate(order.getOrderdate())
                    .orderrec(order.getOrderrec())
                    .orderphone(order.getOrderphone())
                    .orderaddress(order.getOrderaddress())
                    .orderrequest(order.getOrderrequest())
                    .orderstate(order.getOrderstate())
                    .orderprice(order.getOrderprice()).build();
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
    public OrderDto getOrder(Long orderkey){
        Optional<Order> orderWrapper = orderRepository.findById(orderkey);
        Order order = orderWrapper.get();

        OrderDto orderDto = OrderDto.builder()
                .orderkey(order.getOrderkey())
                .memberid(order.getMemberid())
                .orderdate(order.getOrderdate())
                .orderrec(order.getOrderrec())
                .orderphone(order.getOrderphone())
                .orderaddress(order.getOrderaddress())
                .orderrequest(order.getOrderrequest())
                .orderstate(order.getOrderstate())
                .orderprice(order.getOrderprice()).build();

        return orderDto;

    }

    public OrderDetatilDto getOrderDetail(Long orderdetailkey){
        Optional<OrderDetail> orderdetailwrapper = orderDetailRepository.findById(orderdetailkey);
        OrderDetail orderDetail = orderdetailwrapper.get();


            OrderDetatilDto orderDetatilDto =  OrderDetatilDto.builder()
                    .orderdetailkey(orderDetail.getOrderdetailkey())
                    .order(orderDetail.getOrder())
                    .material(orderDetail.getMaterial())
                    .orderdate(orderDetail.getOrderdate())
                    .ordernum(orderDetail.getOrdernum()).build();

            return orderDetatilDto;

    }

    public  List<OrderDetatilDto> getAllOrderDetail(){
        List<OrderDetail> orderDetatilList= orderDetailRepository.findAll();
        List<OrderDetatilDto> orderDetatilDtoList=new ArrayList<>();

        for(OrderDetail orderDetail : orderDetatilList){
            OrderDetatilDto orderDetatilDto =  OrderDetatilDto.builder()
                    .orderdetailkey(orderDetail.getOrderdetailkey())
                    .order(orderDetail.getOrder())
                    .material(orderDetail.getMaterial())
                    .ordernum(orderDetail.getOrdernum()).build();
            orderDetatilDtoList.add(orderDetatilDto);

        }
        return orderDetatilDtoList;
    }

    public Long save(OrderDto orderDto) {

        return orderRepository.save(orderDto.toEntity()).getOrderkey();
    }
    public List<OrderDetatilDto> getOrderDetailList(){
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<OrderDetatilDto> orderDtoList = new ArrayList<>();

        for(OrderDetail orderDetail : orderDetails){
            OrderDetatilDto orderDetatilDto = OrderDetatilDto.builder()
                    .orderdetailkey(orderDetail.getOrderdetailkey())
                    .order(orderDetail.getOrder())
                    .material(orderDetail.getMaterial())
                    .materialname(orderDetail.getMaterialname())
                    .ordernum(orderDetail.getOrdernum())
                    .build();


            orderDtoList.add(orderDetatilDto);


        }
        return orderDtoList;
    }

    public List<Map<String, Object>> getOrderListById(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteOrderDetail(Long id){
        orderDetailRepository.deleteById(id);
    }

    //관리자 주문 목록 구현
    public List<OrderListDto> getMainOrderList(){

        List<Order> orders = orderRepository.findAll();
        List<OrderListDto> orderListDtoList = getOrderList(orders);

        return orderListDtoList;
    }
    //주문 상태 별 목록 만들기
    public List<OrderListDto> getOrderState(Integer orderstate) {

        List<Order> orders = orderRepository.findByOrderstate(orderstate);
        List<OrderListDto> orderListDtoList = getOrderList(orders);

        return orderListDtoList;
    }


    //주문목록 가져오기 메소드
    public List<OrderListDto> getOrderList(List<Order> orders){
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
        List<OrderListDto> orderListDtoList = new ArrayList<>();

        for(Order order:orders){
            String orderre;
            int ordercount;
            String orderstate;
            String orderdate;
            Optional<Member> optionalMember = memberRepository.findById(order.getMemberid());
            Member member = optionalMember.get();

            //받는분
            if(order.getOrderrec() == null || order.getOrderrec().equals("")){
                orderre = member.getUsername();
            }else{
                orderre = order.getOrderrec();
            }

            //주문수량
            List<OrderDetail> orderDetailList = orderDetailRepository.findByAndOrder_Orderkey(order.getOrderkey());
            ordercount = orderDetailList.size();

            //주문날짜
            orderdate = format1.format(order.getOrderdate());

            //주문상태
            String[] state = {"주문완료", "배송준비", "배송완료", "교환,환불신청", "교환,환불완료", "배송취소"};
            orderstate = state[order.getOrderstate()-1];

            OrderListDto orderListDto = OrderListDto.builder()
                    .orderkey(order.getOrderkey())
                    .username(member.getUsername())
                    .orderrec(orderre)
                    .ordercount(ordercount)
                    .orderprice(order.getOrderprice())
                    .orderdate(orderdate)
                    .orderstate(orderstate).build();

            orderListDtoList.add(orderListDto);

        }
        return orderListDtoList;
    }
    //orderkey로 해당 table값 가져오기
    public OrderDto getOrderkey(Long orderkey) {
        Optional<Order> orderOptional = orderRepository.findById(orderkey);
        Order order = orderOptional.get();


        OrderDto orderDto = OrderDto.builder()
                .orderkey(order.getOrderkey())
                .memberid(order.getMemberid())
                .orderdate(order.getOrderdate())
                .orderrec(order.getOrderrec())
                .orderphone(order.getOrderphone())
                .orderaddress(order.getOrderaddress())
                .orderrequest(order.getOrderrequest())
                .orderstate(order.getOrderstate())
                .orderprice(order.getOrderprice()).build();
        return orderDto;
    }

    //
    public List<OrderDetatilDto> getOrderDetailKey(Long orderkey) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByAndOrder_Orderkey(orderkey);
        List<OrderDetatilDto> orderDetatilDtoList = new ArrayList<>();


        for(OrderDetail orderDetail:orderDetailList){
            OrderDetatilDto orderDetatilDto = OrderDetatilDto.builder()
                    .orderdetailkey(orderDetail.getOrderdetailkey())
                    .order(orderDetail.getOrder())
                    .material(orderDetail.getMaterial())
                    .materialname(orderDetail.getMaterialname())
                    .ordernum(orderDetail.getOrdernum())
                    .orderdate(orderDetail.getOrderdate())
                    .build();
            orderDetatilDtoList.add(orderDetatilDto);
        }
        return orderDetatilDtoList;
    }
    //주문상태 변경
    @Transactional
    public void orderStateUpdate(OrderDto orderDto, Long orderkey) {
        Optional<Order> optionalOrder = orderRepository.findById(orderkey);
        Order order = optionalOrder.get();

        order.orderStateUpdate(orderDto.getOrderstate());
    }
}
