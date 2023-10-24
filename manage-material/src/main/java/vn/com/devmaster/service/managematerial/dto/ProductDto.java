package vn.com.devmaster.service.managematerial.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link vn.com.devmaster.service.managematerial.dommain.Product}
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    Integer id;
    @Size(max = 500)
    String name;
    String description;
    String notes;
    @Size(max = 550)
    String image;
    Double price;
    Integer quatity;
    LocalDate createdDate;
    LocalDate updatedDate;
    @Size(max = 50)
    String createdBy;
    @Size(max = 50)
    String updatedBy;
    Byte isactive;

    CategoryDto category;


}