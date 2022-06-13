package jac.fsd02.foodorder.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static jac.fsd02.foodorder.constant.ErrorMessage.NAME_IS_REQUIRED_ERROR_MESSAGE;
import static jac.fsd02.foodorder.constant.ErrorMessage.NAME_SIZE_ERROR_MESSAGE;


@Entity
@Table(name="tbl_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//it means auto increment id for the table
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @NotEmpty(message = NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(min=2, message = NAME_SIZE_ERROR_MESSAGE)
    private String itemName;
    private Double itemPrice;
    private String itemSrc;
    private String description;
    private Active active;
}
