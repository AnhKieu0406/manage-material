package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cartItem", schema = "manage-material")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id", referencedColumnName = "ID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;


    private String name;
    private double price;
    private int qty =1;
}
