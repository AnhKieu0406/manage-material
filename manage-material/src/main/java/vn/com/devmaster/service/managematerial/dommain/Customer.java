package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "customer", schema = "manage-material")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 250)
    @Column(name = "NAME", length = 250)
    private String name;

    @Size(max = 50)
    @Column(name = "USERNAME", length = 50)
    private String username;

    @Size(max = 50)
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
    Set<Order> orderSet;


}