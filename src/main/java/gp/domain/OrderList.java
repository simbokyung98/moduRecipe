package gp.domain;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordernum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private Member member;

    @Column(name = "ordertotalprice")
    private int ordertotalprice;

    @Column(name = "orderpaystate")
    private String orderpaystate;

    @Column(name = "orderdeliver")
    private String orderdeliver;

    @Column(name = "orderdate")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;

    @Column(name = "ordermemo")
    private String ordermemo;

    @Builder
    public OrderList(Long ordernum, Member member, int ordertotalprice, String orderpaystate, String orderdeliver, String ordermemo, Date orderdate){
        this.ordernum=ordernum;
        this.member=member;
        this.ordertotalprice=ordertotalprice;
        this.orderdeliver=orderdeliver;
        this.ordermemo=ordermemo;
        this.orderpaystate=orderpaystate;
        this.orderdate=orderdate;
    }

}
