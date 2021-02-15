package view.tm;

public class OrdersTM {
    private int orderid;
    private String orderdate;
    private String cid;

    public OrdersTM() {
    }

    public OrdersTM(int orderid, String orderdate, String cid) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.cid = cid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderid=" + orderid +
                ", orderdate='" + orderdate + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
