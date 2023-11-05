package vn.com.devmaster.service.managematerial.untils;

public class Sql {
    public static final String PRODUCT_IMAGE_BY_ID = "" +
            "select p.ID    id,\n" +
            "       p.NAME  name,\n" +
            "       p.IMAGE image,\n" +
            "       pi.URL  url\n" +
            "from product p\n" +
            "         inner join product_images pi on p.ID = pi.ID_PRODUCT\n" +
            "where p.ID = ?1";


    public static final String PRODUCT_CATEGORY_ID = "" +
            "select p.ID  id\n" +
            "     , p.NAME name\n" +
            "     , p.DESCRIPTION description\n" +
            "     , p.NOTES notes\n" +
            "     , p.IMAGE image\n" +
            "     , p.PRICE price\n" +
            "     , p.ISACTIVE isactice\n" +
            "\n" +
            "from product p \n" +
            "where p.IDCATEGORY = ?1";

    public static final String PRODUCT_CATEGORY_NAME = "" +
            "select c.NAME cateName\n" +
            "     , p.NAME proName\n" +
            ",       p.IMAGE images\n" +
            ",       p.PRICE prices\n" +
            ",       p.ISACTIVE isActives \n" +
            ",       p.NOTES note \n"+
            "from product p\n" +
            "         join category c on p.IDCATEGORY = c.ID";

}
