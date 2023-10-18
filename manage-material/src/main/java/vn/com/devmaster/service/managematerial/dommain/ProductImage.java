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
@Table(name = "product_images", schema = "manage-material")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 250)
    @Column(name = "NAME", length = 250)
    private String name;

    @Size(max = 250)
    @Column(name = "URL", length = 250)
    private String url;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

}