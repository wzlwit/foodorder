package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.model.Cart;

import java.util.List;

public interface CartService {

    Cart addItemToCart(Cart cart);

    List<Cart> getCartListByUserId(Long userId);

    Double getTotalOrderPrice(Long userId);

    void deleteCartById(Long cartId );

    void deleteItemsByUserId(Long userId);

}