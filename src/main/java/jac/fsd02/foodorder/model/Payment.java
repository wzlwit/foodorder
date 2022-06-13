package jac.fsd02.foodorder.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//it means auto increment id for the table
    private Long id;

    private Long userId;
    private Long orderId;
    private Integer transactionNum;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;

    private String receiverName;
    private String receiverAddress;
    private String receiverPostcode;
    private String bankCardNum;
    private String bankCardExpDate;
    private String bankCardCVV;
}
