package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.model.Cart;
import jac.fsd02.foodorder.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    SessionService sessionService;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart addItemToCart(Cart cart) {
        Long userId = sessionService.getUserIdFromSession();
        Long itemId = cart.getItemId();
        Optional<Cart> optCart = cartRepository.findByUserIdAndItemId(userId, itemId);

        if(optCart.isPresent()) {
            Integer newQuantity = cart.getQuantity();
            Cart newCart = optCart.get();
            newCart.setQuantity(newQuantity);
            return cartRepository.save(newCart);
        }else {
            return cartRepository.save(cart);
        }
    }

    @Override
    public List<Cart> getCartListByUserId(Long userId){

        List<Cart> cartList = cartRepository.findCartsByUserId(userId);

        return cartList;
    }

    public Double getTotalOrderPrice(Long userId){
        List<Cart> cartList = getCartListByUserId(userId);

        BigDecimal itemTotalPrice = BigDecimal.ZERO;
        for (Cart cart: cartList) {
            itemTotalPrice = BigDecimal.valueOf(cart.getItemPrice())
                    .multiply(BigDecimal.valueOf(cart.getQuantity()))
                    .add(itemTotalPrice);
        }

        return itemTotalPrice.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public void deleteCartById(Long cartId) {

        cartRepository.deleteById(cartId);
    }

    @Override
    public void deleteItemsByUserId(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

}


