package jac.fsd02.foodorder.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static jac.fsd02.foodorder.constant.ErrorMessage.NAME_IS_REQUIRED_ERROR_MESSAGE;
import static jac.fsd02.foodorder.constant.ErrorMessage.NAME_SIZE_ERROR_MESSAGE;

@Entity
@Table(name="tbl_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//it means auto increment id for the table
    private Long id;
    @NotEmpty(message = NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(min=2, message = NAME_SIZE_ERROR_MESSAGE)
    private String categoryName;
    private String categorySrc;
}
