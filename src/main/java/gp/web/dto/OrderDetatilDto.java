package gp.web.dto;

import gp.domain.Material;
import gp.domain.Order;
import gp.domain.OrderDetail;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDetatilDto {

    private Long orderdetailkey;
    private Order order;
    private Material material;
    private String materialname;
    private int ordernum;
    private int orderprice;

    @Builder
    public OrderDetatilDto(Long orderdetailkey, Order order, Material material, String materialname, int ordernum, int orderprice){
        this.orderdetailkey=orderdetailkey;
        this.order=order;
        this.material=material;
        this.materialname=materialname;
        this.ordernum=ordernum;
        this.orderprice=orderprice;
    }

    public OrderDetail toEntity(){
        OrderDetail orderDetail = OrderDetail.builder()
                .orderdetailkey(orderdetailkey)
                .order(order)
                .material(material)
                .materialname(materialname)
                .ordernum(ordernum)
                .orderprice(orderprice)
                .build();
        return orderDetail;
    }

}
