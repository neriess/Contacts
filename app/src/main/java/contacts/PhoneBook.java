package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;


public class PhoneBook {

    List<Contact> phoneBook;

    public PhoneBook() {
        this.phoneBook = new ArrayList<>();
    }

    public void save(Contact contact) {
        phoneBook.add(contact);
    }

    public void remove(Contact contact) {
        phoneBook.remove(contact);
    }

    public void editPerson(PersonContact contact, String field, String value) {

        switch (field) {
            case "name" -> contact.setName(value);
            case "surname" -> contact.setSurname(value);
            case "birth" -> contact.setBirthDate(value);
            case "gender" -> contact.setGender(value);
            case "number" -> contact.setNumber(value);
            default -> System.out.println("Wrong input");
        }
    }

    public void editOrganization(OrganizationContact contact, String field, String value) {

        switch (field) {
            case "address" -> contact.setAddress(value);
            case "number" -> contact.setNumber(value);
            default -> System.out.println("Wrong input");
        }
    }

    public List<Contact> search(String input) {
        List<Contact> foundContacts = new ArrayList<>();
        BiPredicate<String, String> contains = (x, y) -> x.toLowerCase().contains(y.toLowerCase());
        for (Contact contact : phoneBook) {
            if (contains.test(contact.nameToString(), input) ||
            contains.test(contact.getNumber(), input)) {
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }

    public int count() {
        return phoneBook.size();
    }

    public Contact getContact(int index) {
        return phoneBook.get(index - 1);
    }

    public List<Contact> getList() {
        return phoneBook;
    }
}
