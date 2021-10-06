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
    private String cartCount;

    @Column(length = 45)
    private String cartPrice;

    @Builder
    public Cart(String username, String cartMaterial, String cartImage, String cartCount, String cartPrice){
        this.username = username;
        this.cartMaterial = cartMaterial;
        this.cartImage = cartImage;
        this.cartCount  = cartCount;
        this.cartPrice = cartPrice;
    }


}
