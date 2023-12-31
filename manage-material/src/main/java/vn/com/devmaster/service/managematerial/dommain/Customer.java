package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity

@Table(name = "customer")
public class Customer{
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

    @Column(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "idCustomer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "idCustomer", cascade = CascadeType.ALL)
    private List<CustomerAddress> listAddresses;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "users_role",
//    joinColumns = @JoinColumn(name = "User_Id"),
//    inverseJoinColumns = @JoinColumn(name="Roles_Id"))
//    private Set<Role> roles = new HashSet<>();


//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", address='" + address + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", createdDate=" + createdDate +
//                ", isactive=" + isactive +
//                ", cartItems=" + cartItems.size() +
//                ", orders=" + orders +
//                '}';
//    }


}