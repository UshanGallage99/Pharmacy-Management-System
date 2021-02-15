package view.tm;

public class SheduleTM {
    private String  sid;
    private String doctorid;
    private String sdate;
    private String stime;
    private int qtyonhand;

    public SheduleTM() {
    }

    public SheduleTM(String sid, String doctorid, String sdate, String stime, int qtyonhand) {
        this.sid = sid;
        this.doctorid = doctorid;
        this.sdate = sdate;
        this.stime = stime;
        this.qtyonhand = qtyonhand;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public int getQtyonhand() {
        return qtyonhand;
    }

    public void setQtyonhand(int qtyonhand) {
        this.qtyonhand = qtyonhand;
    }

    @Override
    public String toString() {
        return "SheduleDTO{" +
                "sid='" + sid + '\'' +
                ", doctorid='" + doctorid + '\'' +
                ", sdate='" + sdate + '\'' +
                ", stime='" + stime + '\'' +
                ", qtyonhand=" + qtyonhand +
                '}';
    }
}
