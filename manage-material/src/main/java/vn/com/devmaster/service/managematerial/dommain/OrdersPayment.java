package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders_payment", schema = "manage-material")
public class OrdersPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TOTAL")
    private Integer total;

    @Column(name = "NOTES")
    private String notes;

    @Size(max = 50)
    @Column(name = "STATUS", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDORD")
    private Order idord;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDPAYMENT")
    private PaymentMethod idPayment;




}