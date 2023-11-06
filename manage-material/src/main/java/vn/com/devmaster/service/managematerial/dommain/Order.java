package vn.com.devmaster.service.managematerial.dommain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders", schema = "manage-material")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "IDORDERS", length = 10)
    private String idorders;

    @Column(name = "ORDERS_DATE")
    private Instant ordersDate;



    @Column(name = "TOTAL_MONEY")
    private Double totalMoney;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Size(max = 250)
    @Column(name = "NAME_RECIVER", length = 250)
    private String nameReciver;

    @Size(max = 500)
    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Size(max = 50)
    @Column(name = "PHONE", length = 50)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "IDCUSTOMER")
    private Customer idCustomer;


    @OneToMany(cascade = CascadeType.DETACH,mappedBy = "idord")
    private List<OrdersDetail> ordersDetails;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idord")
    private List<OrdersPayment> ordersPayments;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idord")
    private List<OrdersTransport> ordersTransports;
}