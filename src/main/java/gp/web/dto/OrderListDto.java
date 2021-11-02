package gp.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderListDto {
    private Long orderkey;

    private String username;

    private String orderrec;

    private int ordercount;

    private Integer orderprice;

    private String orderdate;

    private String orderstate;

    @Builder
    public OrderListDto(Long orderkey, String username, String orderrec, int ordercount, Integer orderprice,
                        String orderdate, String orderstate){
        this.orderkey = orderkey;
        this.username = username;
        this.orderrec = orderrec;
        this.ordercount = ordercount;
        this.orderprice = orderprice;
        this.orderdate = orderdate;
        this.orderstate = orderstate;
    }



}
