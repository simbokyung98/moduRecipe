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
    private String cartCount;
    private String cartPrice;

    @Builder
    public CartDto(Long cartKey, String username, String cartMaterial, String cartImage, String cartCount, String cartPrice){
        this.cartKey = cartKey;
        this.username = username;
        this.cartMaterial = cartMaterial;
        this.cartImage = cartImage;
        this.cartCount = cartCount;
        this.cartPrice = cartPrice;
    }

    public Cart toEntity(){
        return  Cart.builder()
                .cartKey(cartKey)
                .username(username)
                .cartMaterial(cartMaterial)
                .cartImage(cartImage)
                .cartCount(cartCount)
                .cartPrice(cartPrice)
                .build();
    }
}
