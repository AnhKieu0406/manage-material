package vn.com.devmaster.service.managematerial.dommain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "category", schema = "manage-material")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "IDPARENT")
    private Integer idparent;

    @Size(max = 500)
    @Column(name = "NAME", length = 500)
    private String name;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Size(max = 250)
    @Column(name = "ICON", length = 250)
    private String icon;


    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss'")
    @Column(name = "CREATED_DATE")
    private Instant createdDate;


    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss'")
    @Column(name = "UPDATED_DATE")
    private Instant updatedDate;

    @Size(max = 50)
    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Size(max = 50)
    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Column(name = "ISACTIVE")
    private Byte isactive;

    @OneToMany(targetEntity = Product.class,mappedBy = "idcategory",orphanRemoval = false, fetch = FetchType.LAZY)
    Set<Product> list ;



}