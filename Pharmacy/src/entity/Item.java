package entity;

public class Item implements SuperEntity {
    private String  code;
    private String discription;
    private String packsize;
    private double unitprice;
    private int qtyonhand;
    private String location;



    public Item(String code, String discription, String packsize, double unitprice, int qtyonhand, String location) {
        this.code = code;
        this.discription = discription;
        this.packsize = packsize;
        this.unitprice = unitprice;
        this.qtyonhand = qtyonhand;
        this.location = location;
    }

    public Item(String itemcode, int orderqty) {

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

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getQtyonhand() {
        return qtyonhand;
    }

    public void setQtyonhand(int qtyonhand) {
        this.qtyonhand = qtyonhand;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", discription='" + discription + '\'' +
                ", packsize='" + packsize + '\'' +
                ", unitprice=" + unitprice +
                ", qtyonhand=" + qtyonhand +
                ", location='" + location + '\'' +
                '}';
    }
}
