package jac.fsd02.foodorder.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="tbl_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//it means auto increment id for the table
    private Long id;

    private Long paymentId;
    private Long userId;
    private Double itemTotalPrice;
    private Double tax;
    private Double shippingFee;
    private Double orderTotalPrice;
    private Date orderDate;
    private OrderStatus orderStatus;
}
