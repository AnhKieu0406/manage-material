package vn.com.devmaster.service.managematerial.dommain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "product", schema = "manage-material")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 500)
    @Column(name = "NAME", length = 500)
    private String name;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Size(max = 550)
    @Column(name = "IMAGE", length = 550)

    private String image;


    @Column(name = "PRICE")
    private double price;

    @Column(name = "QUATITY")
    private Integer quatity;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Size(max = 50)
    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Size(max = 50)
    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Column(name = "ISACTIVE")
    private Byte isactive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCATEGORY")
    private Category idcategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<ProductImage> list ;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    List<OrdersDetail> ordersDetails;
}