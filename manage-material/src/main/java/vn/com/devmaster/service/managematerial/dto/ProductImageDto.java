package vn.com.devmaster.service.managematerial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link vn.com.devmaster.service.managematerial.dommain.ProductImage}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductImageDto  {
    Integer id;
    @Size(max = 250)
    String name;
    @Size(max = 250)
    String url;
}