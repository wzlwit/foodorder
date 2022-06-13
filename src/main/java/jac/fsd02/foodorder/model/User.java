package jac.fsd02.foodorder.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//it means auto increment id for the table
    private Long id;
    private String name;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    private String phoneNumber;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="city_Id")
    private City city;
    private String address;
    @Column(nullable = false, unique = true, length = 30)
    private String userName;
    @Column(nullable = false, length = 64)
    private String password;
    private UserType userType;
}
