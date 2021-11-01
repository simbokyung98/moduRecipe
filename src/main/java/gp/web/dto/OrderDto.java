package gp.web.dto;

import gp.domain.Order;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long orderkey;

    private Long memberid;

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
                .memberid(memberid)
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
    public OrderDto(Long orderkey, Long memberid, Date orderdate, String orderrec, String orderphone, String orderrequest, String orderaddress, Integer orderstate, Integer orderprice){
        this.orderkey=orderkey;
        this.memberid=memberid;
        this.orderdate=orderdate;
        this.orderrec=orderrec;
        this.orderphone=orderphone;
        this.orderaddress=orderaddress;
        this.orderrequest=orderrequest;
        this.orderstate=orderstate;
        this.orderprice=orderprice;
    }

}
