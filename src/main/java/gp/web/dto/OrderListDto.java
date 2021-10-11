package gp.web.dto;

import gp.domain.Member;
import gp.domain.OrderList;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderListDto {
    private Long ordernum;
    private Member member;
    private int ordertotalprice;
    private String orderpaystate;
    private String orderdeliver;
    private Date orderdate;
    private String ordermemo;

    public OrderList toEntity(){
        OrderList orderList = OrderList.builder()
                .ordernum(ordernum)
                .member(member)
                .ordertotalprice(ordertotalprice)
                .orderpaystate(orderpaystate)
                .orderdeliver(orderdeliver)
                .orderdate(orderdate)
                .ordermemo(ordermemo)
                .build();

        return orderList;
    }
    @Builder
    public OrderListDto(Long ordernum, Member member, int ordertotalprice, String orderpaystate, String orderdeliver, String ordermemo, Date orderdate){
        this.ordernum=ordernum;
        this.member=member;
        this.ordertotalprice=ordertotalprice;
        this.orderdeliver=orderdeliver;
        this.ordermemo=ordermemo;
        this.orderpaystate=orderpaystate;
        this.orderdate=orderdate;
    }


}
