package vn.com.devmaster.service.managematerial.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link vn.com.devmaster.service.managematerial.dommain.Order}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    Integer id;
    @Size(max = 10)
    String idorders;
    Instant ordersDate;
    Double totalMoney;
    String notes;
    @Size(max = 250)
    String nameReciver;
    @Size(max = 500)
    String address;
    @Size(max = 50)
    String phone;
    CustomerDto customer;

    List<OrdersDetailDto> ordersDetailDto;
    public static class  OrderDetailDto{
        private String idProduct;
        private String price;
        private Long qty;
    }


}