package gp.service;

import gp.domain.Order;
import gp.domain.OrderDetail;
import gp.domain.OrderDetailRepository;
import gp.domain.OrderRepository;
import gp.web.dto.OrderDetatilDto;
import gp.web.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderDetailRepository orderDetailRepository;

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
                    .ordernum(orderDetail.getOrdernum())
                    .orderprice(orderDetail.getOrderprice()).build();

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
                    .ordernum(orderDetail.getOrdernum())
                    .orderprice(orderDetail.getOrderprice()).build();
            orderDetatilDtoList.add(orderDetatilDto);

        }
        return orderDetatilDtoList;
    }

    public Long save(OrderDto orderDto) {

        return orderRepository.save(orderDto.toEntity()).getOrderkey();
    }
}
