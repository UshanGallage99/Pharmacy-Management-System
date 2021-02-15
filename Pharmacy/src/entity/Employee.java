package entity;

public class Employee implements SuperEntity {
    private String  employeeID;
    private String ename;
    private String eaddress;
    private String econtact;
    private String eemail;

    public Employee() {
    }

    public Employee(String employeeID, String ename, String eaddress, String econtact, String eemail) {
        this.employeeID = employeeID;
        this.ename = ename;
        this.eaddress = eaddress;
        this.econtact = econtact;
        this.eemail = eemail;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress;
    }

    public String getEcontact() {
        return econtact;
    }

    public void setEcontact(String econtact) {
        this.econtact = econtact;
    }

    public String getEemail() {
        return eemail;
    }

    public void setEemail(String eemail) {
        this.eemail = eemail;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeID='" + employeeID + '\'' +
                ", ename='" + ename + '\'' +
                ", eaddress='" + eaddress + '\'' +
                ", econtact='" + econtact + '\'' +
                ", eemail='" + eemail + '\'' +
                '}';
    }
}
