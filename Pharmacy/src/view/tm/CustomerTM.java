package view.tm;

import entity.SuperEntity;

public class CustomerTM implements SuperEntity {
    private String  NIC;
    private String name;
    private String address;
    private String contact;

    public CustomerTM() {
    }

    public CustomerTM(String NIC, String name, String address, String contact) {
        this.NIC = NIC;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "NIC='" + NIC + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
