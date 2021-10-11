package gp.web.dto;

import gp.domain.Member;
import gp.domain.Order;
import lombok.Builder;

import javax.persistence.*;
import java.util.Date;

public class OrderDto {
    private Long orderkey;

    private Member member;

    private Date orderdate;

    private String orderrec;

    private String orderaddress;

    private String orderphone;

    private String orderrequest;

    private Integer orderstate;

    private Integer orderprice;

    public Order toEntity(){
        Order order=Order.builder()
                .orderkey(orderkey)
                .member(member)
                .orderdate(orderdate)
                .orderrec(orderrec)
                .orderaddress(orderaddress)
                .orderphone(orderphone)
                .orderrequest(orderrequest)
                .orderstate(orderstate)
                .orderprice(orderprice)
                .build();
        return  order;


    }
    @Builder
    public OrderDto(Long orderkey,Member member,Date orderdate, String orderrec,String orderphone,String orderrequest,String orderaddress, Integer orderstate, Integer orderprice){
        this.orderkey=orderkey;
        this.member=member;
        this.orderdate=orderdate;
        this.orderrec=orderrec;
        this.orderphone=orderphone;
        this.orderaddress=orderaddress;
        this.orderrequest=orderrequest;
        this.orderstate=orderstate;
        this.orderprice=orderprice;
    }
}
