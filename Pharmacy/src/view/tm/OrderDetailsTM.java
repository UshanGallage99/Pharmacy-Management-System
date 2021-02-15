package view.tm;

public class OrderDetailsTM {
    private String  code;
    private String discription;
    private String location;
    private double unitprice;
    private int qty;
    private double total;

    public OrderDetailsTM(String code, String discription, String location, double unitprice, int qty, double total) {
        this.setCode(code);
        this.setDiscription(discription);
        this.setLocation(location);
        this.setUnitprice(unitprice);
        this.setQty(qty);
        this.setTotal(total);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailsTM{" +
                "code='" + code + '\'' +
                ", discription='" + discription + '\'' +
                ", location='" + location + '\'' +
                ", unitprice=" + unitprice +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
