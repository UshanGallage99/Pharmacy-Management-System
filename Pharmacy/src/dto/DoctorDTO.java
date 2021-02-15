package dto;

public class DoctorDTO {
    private String  doctorID;
    private String doctorname;
    private String email;
    private String contact;

    public DoctorDTO() {
    }

    public DoctorDTO(String doctorID, String doctorname, String email, String contact) {
        this.doctorID = doctorID;
        this.doctorname = doctorname;
        this.email = email;
        this.contact = contact;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "doctorID='" + doctorID + '\'' +
                ", doctorname='" + doctorname + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
