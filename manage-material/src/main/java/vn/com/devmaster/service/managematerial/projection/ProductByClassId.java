package vn.com.devmaster.service.managematerial.projection;

import java.time.Instant;

public interface ProductByClassId {
    Integer getId();
    String  getName();
    String  getDescription();
    String  getNotes();
    String  getImage();
    Double  getPrice();
    Integer  getQuantity();
    Instant getCreateDate();
    Instant getUpDateDate();
    String  getCreateBy();
    String  getUpdateBy();
    Integer getIsactive();
}
