package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.model.Cart;
import org.springframework.ui.Model;

public interface CartController {

    String addItemToCart(Cart cart);

    String getCartListByUser(Long userid, Model model);

    void deleteCartListByUser(Long userId);

    String deleteCartById(Long id);
}
