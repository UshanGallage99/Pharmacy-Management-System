package dto;

public class OrderDetailsDTO {
    private int  orderid;
    private String itemcode;
    private int orderqty;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int orderid, String itemcode, int orderqty) {
        this.orderid = orderid;
        this.itemcode = itemcode;
        this.orderqty = orderqty;
    }

    /*public OrderDetailsDTO(String itemcode, int qty) {
        this.itemcode = itemcode;
        this.orderqty = qty;
    }*/

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public int getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(int orderqty) {
        this.orderqty = orderqty;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderid=" + orderid +
                ", itemcode='" + itemcode + '\'' +
                ", orderqty=" + orderqty +
                '}';
    }
}
