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

public class CustomerDto {

    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String name;

    private String username;

    @Size(min = 3, max = 15, message = "Password contains 3-10 characters")
    private String password;


    private String confirmPassword;
}