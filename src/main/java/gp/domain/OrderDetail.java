package gp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name ="orderdetail")
public class OrderDetail {

    @Id
    @Column(name = "orderdetailkey")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderdetailkey;

    @JoinColumn(name = "orderkey")
    @OneToOne
    private Order order;

    @JoinColumn(name = "materialkey")
    @ManyToOne
    private Material material;

    @Column(name = "materialname")
    private String materialname;


    @Column(name="orderdate")
    private Date orderdate;

    @Column(name = "ordernum")
    private int ordernum;

    @Builder
    public OrderDetail(Long orderdetailkey, Order order, Material material, String materialname, int ordernum, Date orderdate){
        this.orderdetailkey=orderdetailkey;
        this.order=order;
        this.material=material;
        this.materialname=materialname;
        this.orderdate=orderdate;
        this.ordernum=ordernum;
    }


}
