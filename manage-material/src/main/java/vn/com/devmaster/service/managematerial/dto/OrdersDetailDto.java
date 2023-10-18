package vn.com.devmaster.service.managematerial.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.com.devmaster.service.managematerial.dommain.OrdersDetail}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter

public class OrdersDetailDto  {
    Integer id;
    Double price;
    Integer qty;
}