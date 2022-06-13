package jac.fsd02.foodorder.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//it means auto increment id for the table
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "item_Id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long itemId;
//    @ManyToOne
//    @JoinColumn(name = "order_Id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long orderId;

    private Double price;

    private Integer quantity;
}
