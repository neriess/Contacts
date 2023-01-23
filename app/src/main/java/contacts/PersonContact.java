package contacts;

public class PersonContact extends Contact {

    private String surname;
    private String birthDate;
    private String gender;

    public PersonContact(String name, String number, String surname, String birthDate, String gender) {
        super(name, number);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        String regex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        if(birthDate.matches(regex)) {
            this.birthDate = birthDate;
        } else {
            System.out.println("Bad birth date!");
            this.birthDate = "[no data]";
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        String regex = "^[M|F]$";
        if(gender.matches(regex)) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
    }

    @Override
    public String toString() {
        return String.format("""
                        Name: %s
                        Surname: %s
                        Birth date: %s
                        Gender: %s
                        Number: %s
                        Time created: %s
                        Time last edit: %s""",super.getName(), surname, birthDate, gender, super.getNumber(),
                super.getCreatedTime(), super.getEditedTime());
    }

    public String nameToString() {
        return String.format("%s %s", super.getName(), surname);
    }
}
