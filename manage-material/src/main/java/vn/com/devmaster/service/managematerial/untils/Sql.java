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
           "select p.ID           id\n" +
            "     , p.NAME         name\n" +
            "     , p.DESCRIPTION  description\n" +
            "     , p.NOTES        notes\n" +
            "     , p.IMAGE        image\n" +
            "     , p.PRICE        price\n" +
            "     , p.QUATITY      quatity\n" +
            "     , p.CREATED_DATE createDate\n" +
            "     , p.UPDATED_DATE updateDate\n" +
            "     , p.CREATED_BY   createBy\n" +
            "     , p.UPDATED_BY   updateBy\n" +
            "     , p.ISACTIVE     isactice\n" +
            "\n" +
            "from product p\n" +
            "where p.IDCATEGORY = ?1";

    public static final String PRODUCT_CATEGORY_NAME = "" +
            "select c.NAME cateName\n" +
            "     , p.NAME proName\n" +
            "       ,p.ID proId \n" +
            ",       p.IMAGE images\n" +
            ",       p.PRICE prices\n" +
            ",       p.QUATITY quantity\n" +
            ",       p.ISACTIVE isActives \n" +
            ",       p.NOTES note \n" +
            "from product p\n" +
            "         join category c on p.IDCATEGORY = c.ID";


    public static final String ORDER_ALL_INFOMATION = "" +
            "select  o.ID  ordId,\n" +
            "        c.NAME   cusName,\n" +
            "        p.NAME  proName,\n" +
            "        od.PRICE orDetPrice,\n" +
            "        o.NAME_RECIVER ordName,\n" +
            "        op.NOTES ordPayNote,\n" +
            "        tm.NOTES    tranNote\n" +
            "from orders o\n" +
            "    inner join customer c on o.IDCUSTOMER = c.ID\n" +
            "    inner join orders_details od on o.ID = od.IDORD\n" +
            "    inner join product p on od.IDPRODUCT = p.ID\n" +
            "    inner join orders_payment op on o.ID = op.IDORD\n" +
            "    inner join  orders_transport ot on o.ID = ot.IDORD\n" +
            "inner join transport_method tm on ot.IDTRANSPORT = tm.ID\n" +
            "where c.ID = ?";

    public static final String CUSTOMER_ADDRESS_BY_ID_CUSTOMER = "" +
            "select cd.*  customer_address cd " +
            "           where cd.ID = :?";
}
