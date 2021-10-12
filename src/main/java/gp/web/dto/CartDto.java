package gp.web.dto;

import gp.domain.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {


    private Long cartKey;
    private String username;
    private String cartMaterial;
    private String cartImage;
    private int cartCount;
    private String cartPrice;
    private String cartCapacity;

    @Builder
    public CartDto(Long cartKey, String username, String cartMaterial, String cartImage, int cartCount, String cartPrice, String cartCapacity){
        this.cartKey = cartKey;
        this.username = username;
        this.cartMaterial = cartMaterial;
        this.cartImage = cartImage;
        this.cartCount = cartCount;
        this.cartPrice = cartPrice;
        this.cartCapacity = cartCapacity;
    }

    public Cart toEntity(){
        return  Cart.builder()
                .cartKey(cartKey)
                .username(username)
                .cartMaterial(cartMaterial)
                .cartImage(cartImage)
                .cartCount(cartCount)
                .cartPrice(cartPrice)
                .cartCapacity(cartCapacity)
                .build();
    }
}
