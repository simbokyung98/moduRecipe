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
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "orderkey")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderkey;

    @JoinColumn(name = "id")
    @ManyToOne
    private Member member;

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

    @Builder
    public Order(Long orderkey,Member member,Date orderdate, String orderrec,String orderphone,String orderrequest,String orderaddress, int orderstate, int orderprice){
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
