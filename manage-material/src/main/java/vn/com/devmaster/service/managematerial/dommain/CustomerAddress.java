package vn.com.devmaster.service.managematerial.dommain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer_address", schema = "manage-material")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDCUSTOMER")
    private Customer idCustomer;

    @Size(max = 500)
    @Column(name = "NAME", length = 500)
    private String name;
    @Size(max = 500)
    @Column(name = "ADRESS", length = 500)
    private String adress;

    @Size(max = 50)
    @Column(name = "PHONE", length = 50)
    private String phone;

}