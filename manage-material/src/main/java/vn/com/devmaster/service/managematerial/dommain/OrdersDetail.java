package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders_details", schema = "manage-material")
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QTY")
    private Integer qty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDORD")
    private Order idord;


    @ManyToOne
    @JoinColumn(name = "IDPRODUCT")
    private Product idproduct;


}