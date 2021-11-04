package gp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @Column(name = "orderkey")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderkey;

    @Column(name = "memberid")
    private Long memberid;

    @Column(name = "orderdate")
    private Date orderdate;

    @Column (name = "orderrec")
    private String orderrec;

    @Column(name = "orderaddress")
    private String orderaddress;

    @Column(name = "orderphone")
    private String orderphone;

    @Column(name = "orderrequest")
    private String orderrequest;

    @Column(name = "orderstate")
    private Integer orderstate;

    @Column(name = "orderprice")
    private Integer orderprice;

    public Long getOrderkey(){
        return orderkey;
    }

    @Builder
    public Order(Long orderkey, Long memberid, Date orderdate, String orderrec, String orderphone, String orderrequest, String orderaddress, int orderstate, int orderprice){
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

    //주문상태 업데이터
    public void orderStateUpdate(Integer orderstate){
        this.orderstate = orderstate;
    }

}
