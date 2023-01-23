package contacts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Contact {

    private String name;
    private String number;
    private LocalDateTime createdTime;
    private LocalDateTime editedTime;

    public Contact(String name, String number) {
        this.name = name;
        setNumber(number);
        createdTime = LocalDateTime.now();
        editedTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        String regex = "([+])?((\\w+[ -]\\w{2,})|(\\(\\w+\\)[ -]\\w{2,})|(\\w+[ -]\\(\\w{2,}\\))|(\\w+)|(\\(\\w+\\)))" +
                "([ -]\\w{2,})*";
        if (number.matches(regex)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "[no number]";
        }
    }

    public String getCreatedTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(createdTime);
    }


    public String getEditedTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(editedTime);
    }

    public void setEditedTime() {
        this.editedTime = LocalDateTime.now();
    }

    public abstract String nameToString();
}
