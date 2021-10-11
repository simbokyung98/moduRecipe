package gp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartKey;

    @Column(length = 45)
    private String username;

    @Column(length = 45)
    private String cartMaterial;

    @Column(length = 45)
    private String cartImage;

    @Column(length = 45)
    private int cartCount;

    @Column(length = 45)
    private String cartPrice;

    @Column(length = 45)
    private String cartCapacity;

    @Builder
    public Cart(String username, String cartMaterial, String cartImage, int cartCount, String cartPrice, String cartCapacity){
        this.username = username;
        this.cartMaterial = cartMaterial;
        this.cartImage = cartImage;
        this.cartCount  = cartCount;
        this.cartPrice = cartPrice;
        this.cartCapacity = cartCapacity;
    }


    //장바구니 수량 업데이트
    public void update(int cartCount){

        this.cartCount += cartCount;
    }

    //장바구니 수량 +1
    public void up(int cartCount){

        this.cartCount += 1;
    }
    //장바구니 수량 -1
    public void down(int cartCount){

        this.cartCount -= 1;
    }



}
