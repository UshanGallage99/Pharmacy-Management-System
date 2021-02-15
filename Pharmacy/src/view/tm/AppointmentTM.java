package view.tm;

public class AppointmentTM {
    private int appointmentID;
    private String cid;
    private String sid;
    private String date;
    private double price;
    private int qty;

    public AppointmentTM() {
    }

    public AppointmentTM(int appointmentID, String cid, String sid, String date, double price, int qty) {
        this.appointmentID = appointmentID;
        this.cid = cid;
        this.sid = sid;
        this.date = date;
        this.price = price;
        this.qty = qty;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "appointmentID=" + appointmentID +
                ", cid='" + cid + '\'' +
                ", sid='" + sid + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
