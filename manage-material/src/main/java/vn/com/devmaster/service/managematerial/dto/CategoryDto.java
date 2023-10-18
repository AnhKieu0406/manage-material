package vn.com.devmaster.service.managematerial.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;


/**
 * DTO for {@link vn.com.devmaster.service.managematerial.dommain.Category}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDto implements Serializable {
    Integer id;
    Integer idparent;
    @Size(max = 500)
    String name;
    String notes;
    @Size(max = 250)
    String icon;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss'")
    Instant createdDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss'")
    String updatedDate;
    @Size(max = 50)
    String createdBy;
    @Size(max = 50)
    String updatedBy;
    Byte isactive;

//    public void setCreatedDate(String createdDate) {
//        LocalDate dateTime =LocalDate.parse(createdDate);
//        this.createdDate = String.valueOf(dateTime);
//    }
//
//    public void setUpdatedDate(String updatedDate) {
//        this.updatedDate = updatedDate;
//    }
}