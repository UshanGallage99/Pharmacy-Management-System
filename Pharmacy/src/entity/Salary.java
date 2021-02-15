package entity;

public class Salary implements SuperEntity {
    private String eid;
    private double basicsalary;
    private double epf;
    private double etf;
    private double totalsalary;

    public Salary(String string, double aDouble, double setDouble) {
        this.eid=string;
        this.basicsalary=aDouble;
        this.totalsalary=setDouble;

    }

    public Salary( String eid, double basicsalary, double epf, double etf, double totalsalary) {

        this.eid = eid;
        this.basicsalary = basicsalary;
        this.epf = epf;
        this.etf = etf;
        this.totalsalary = totalsalary;
    }


    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public double getBasicsalary() {
        return basicsalary;
    }

    public void setBasicsalary(double basicsalary) {
        this.basicsalary = basicsalary;
    }

    public double getEpf() {
        return epf;
    }

    public void setEpf(double epf) {
        this.epf = epf;
    }

    public double getEtf() {
        return etf;
    }

    public void setEtf(double etf) {
        this.etf = etf;
    }

    public double getTotalsalary() {
        return totalsalary;
    }

    public void setTotalsalary(double totalsalary) {
        this.totalsalary = totalsalary;
    }

    @Override
    public String toString() {
        return "SalaryDTO{" +
                ", eid='" + eid + '\'' +
                ", basicsalary=" + basicsalary +
                ", epf=" + epf +
                ", etf=" + etf +
                ", totalsalary=" + totalsalary +
                '}';
    }
}
