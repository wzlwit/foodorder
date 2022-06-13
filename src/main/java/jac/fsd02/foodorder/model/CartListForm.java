package jac.fsd02.foodorder.model;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartListForm {

    private ArrayList<Cart> cartList;

    private Double tax;

    private Double shippingFee;

    private Double itemTotalPrice;

    private Double orderTotalPrice;

}
