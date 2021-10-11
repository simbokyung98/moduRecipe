package gp.service;

import gp.domain.Cart;
import gp.domain.CartRepository;
import gp.web.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyService {

    private final CartRepository cartRepository;

    //해당 제품 있는지 확인
    public String findCart(String cartMaterial){
        List<Cart> cartList = cartRepository.findAll();

        for (Cart cart : cartList){
            if(cart.getCartMaterial().equals(cartMaterial)){
                return cartMaterial;
            }
        }
        return null;
    }

    //새제품 장바구니에 넣기
    public Long saveCart(CartDto cartDto){
        return cartRepository.save(cartDto.toEntity()).getCartKey();
    }

    public List<CartDto> getCart(String username){
        List<Cart> cartList = cartRepository.findByUsername(username);
        List<CartDto> cartDtoList = new ArrayList<>();

        for(Cart cart : cartList){
            CartDto cartDto = CartDto.builder()
                    .cartKey(cart.getCartKey())
                    .username(cart.getUsername())
                    .cartMaterial(cart.getCartMaterial())
                    .cartImage(cart.getCartImage())
                    .cartCount(cart.getCartCount())
                    .cartCapacity(cart.getCartCapacity())
                    .cartPrice(cart.getCartPrice()).build();
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }

    //원래 제품 수량 업데이트
    @Transactional
    public void cartupdate(String cartMaterial, CartDto cartDto){
        Optional<Cart> cartList = cartRepository.findByCartMaterial(cartMaterial);

        //Optional로 해야 get() 가능
        Cart cart = cartList.get();
        cart.update(cartDto.getCartCount());


    }
    //장바구니 제품 삭제
    @Transactional
    public void cartDelete(Long cartKey){
        Optional<Cart> optionalCart = cartRepository.findById(cartKey);
        Cart cart = optionalCart.get();

        cartRepository.delete(cart);
    }
    //선택 상품 수량 +1 UP 업데이트
    @Transactional
    public void cartUp(Long cartKey){
        Optional<Cart> cartOptional = cartRepository.findById(cartKey);
        Cart cart = cartOptional.get();
        cart.up(cart.getCartCount());
    }

    //선택 상품 수량 -1 down 업데이트
    @Transactional
    public void cartDown(Long cartKey){
        Optional<Cart> cartOptional = cartRepository.findById(cartKey);
        Cart cart = cartOptional.get();
        cart.down(cart.getCartCount());
    }





}
