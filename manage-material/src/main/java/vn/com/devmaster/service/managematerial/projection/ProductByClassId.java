package vn.com.devmaster.service.managematerial.projection;

public interface ProductByClassId {
    Integer getId();
    String  getName();
    String  getDescription();
    String  getNotes();
    String  getImage();
    Double  getPrice();
    Integer getIsactive();
}
