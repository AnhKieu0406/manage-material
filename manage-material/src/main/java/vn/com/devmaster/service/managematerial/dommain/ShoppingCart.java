package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_cart", schema = "manage-material")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "customer_id", referencedColumnName = "ID")
//    private Customer customer;

    private double totalPrice;

    private int totalItems;

//    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "cart")
//    private Set<CartItem> cartItems;

//    public ShoppingCart() {
//        this.cartItems = new HashSet<>();
//        this.totalItems = 0;
//        this.totalPrice = 0.0;
//    }

}
