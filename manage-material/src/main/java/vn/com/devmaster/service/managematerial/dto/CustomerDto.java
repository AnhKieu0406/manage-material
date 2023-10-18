package vn.com.devmaster.service.managematerial.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link vn.com.devmaster.service.managematerial.dommain.Customer}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto  {
    Integer id;
    @Size(max = 250)
    String name;
    @Size(max = 50)
    String username;
    @Size(max = 50)
    String password;
    @Size(max = 500)
    String address;
    @Size(max = 150)
    String email;
    @Size(max = 50)
    String phone;
    Instant createdDate;
    Byte isactive;
}