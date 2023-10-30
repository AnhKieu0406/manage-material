package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "manage-material")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(min = 5, max = 20,message = "Name should have 5- 20 characters")
    @Column(name = "NAME", length = 250)
    private String name;

    @Size(min = 5, max = 20,message = "Username should have 5- 20 characters")
    @Column(name = "USERNAME", length = 20)
    private String username;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Size(max = 500)
    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Size(max = 150)
    @Column(name = "EMAIL", length = 150)
    private String email;

    @Size(max = 50)
    @Column(name = "PHONE", length = 50)
    private String phone;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @Column(name = "ISACTIVE")
    private Byte isactive;


    @OneToMany(targetEntity = Order.class,mappedBy = "customer",orphanRemoval = false,fetch = FetchType.LAZY)
    List<Order> orderSet;

//    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//    private ShoppingCart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;



}