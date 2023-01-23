package contacts;

public class OrganizationContact extends Contact {

    private String address;

    public OrganizationContact(String name, String number, String address) {
        super(name, number);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("""
                        Organization name: %s
                        Address: %s
                        Number: %s
                        Time created: %s
                        Time last edit: %s""", super.getName(), address, super.getNumber(),
                super.getCreatedTime(), super.getEditedTime());
    }

    public String nameToString() {
        return super.getName();
    }
}
