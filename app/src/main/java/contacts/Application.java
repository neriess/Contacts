package contacts;

import java.util.List;
import java.util.Scanner;

public class Application {
    Scanner scn;
    PhoneBook phoneBook;

    Application() {
        this.scn = new Scanner(System.in);
        this.phoneBook = new PhoneBook();
    }

    public void run() {
        while (true) {
            System.out.print("\nEnter action (add, list, search, count, exit): ");
            String action = scn.nextLine();

            switch (action) {
                case "add" -> add();
                case "list" -> list();
                case "search" -> search();
                case "count" -> count();
                case "exit" -> System.exit(0);
                default -> System.out.println("Wrong input");
            }
        }
    }

    private void add() {
        System.out.println("Enter the type (person, organization):");
        String input = scn.nextLine();
        switch (input) {
            case "person" -> addPerson();
            case "organization" -> addOrganization();
            default -> System.out.println("Wrong input");
        }
    }

    private void addPerson() {
        String birthDateRegex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        String genderRegex = "^[M|F]$";
        String noData = "[no data]";

        System.out.println("Enter the name:");
        String name = scn.nextLine();
        System.out.println("Enter the surname:");
        String surname = scn.nextLine();
        System.out.println("Enter the birth date:");
        String birthDate = scn.nextLine();
        if (!birthDate.matches(birthDateRegex)) {
            System.out.println("Bad birth date!");
            birthDate = noData;
        }
        System.out.println("Enter the gender (M, F):");
        String gender = scn.nextLine();
        if (!gender.matches(genderRegex)) {
            System.out.println("Bad gender!");
            gender = noData;
        }
        System.out.println("Enter the number:");
        String number = scn.nextLine();

        phoneBook.save(new PersonContact(name, number, surname, birthDate, gender));
        System.out.println("The record added.");
    }

    private void addOrganization() {
        System.out.println("Enter the organization name:");
        String name = scn.nextLine();
        System.out.println("Enter the address:");
        String address = scn.nextLine();
        System.out.println("Enter the number:");
        String number = scn.nextLine();

        phoneBook.save(new OrganizationContact(name, number, address));
        System.out.println("The record added.");
    }

    private void remove(Contact contact) {
        phoneBook.remove(contact);
        System.out.println("The record removed!");
    }

    private void edit(Contact contact) {

        if(contact.getClass() == PersonContact.class) {
            System.out.println("Select a field (name, surname, birth, gender, number):");
            String field = scn.nextLine();
            System.out.printf("Enter %s:\n", field);
            String value = scn.nextLine();
            phoneBook.editPerson((PersonContact) contact, field, value);
            System.out.println("The record updated!");
        }
        if(contact.getClass() == OrganizationContact.class) {
            System.out.println("Select a field (name, surname, birth, gender, number):");
            String field = scn.nextLine();
            System.out.printf("Enter %s:\n", field);
            String value = scn.nextLine();
            phoneBook.editOrganization((OrganizationContact) contact, field, value);
            System.out.println("The record updated!");
        }
    }

    private void count() {
        System.out.printf("The Phone Book has %s records.\n", phoneBook.count());
    }

    private void list() {
        List<Contact> contacts = phoneBook.getList();
        if(contacts.isEmpty()) {
            System.out.println("The Phone Book is empty");
            return;
        }
        printContacts(contacts);
        System.out.println("Enter action ([number], back):");
        String input = scn.nextLine();
        if (input.equals("back")) {
            return;
        } else {
            try {
                int index = Integer.parseInt(input);
                Contact contact = phoneBook.getContact(index);
                System.out.println(contact.toString());
                contactMenu(contact);
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }

    private void search() {
        boolean back = false;
        do {
            System.out.println("Enter search query:");
            String input = scn.nextLine();
            List<Contact> foundContacts = phoneBook.search(input);
            System.out.printf("Found %d result:\n", foundContacts.size());
            printContacts(foundContacts);
            System.out.print("\nEnter action ([number], back, again):\n");
            input = scn.nextLine();
            if (input.equals("back")) {
                back = true;
            } else if (input.equals("again")) {
                continue;
            } else {
                try {
                    int index = Integer.parseInt(input);
                    Contact contact = foundContacts.get(index - 1);
                    System.out.println(contact.toString());
                    contactMenu(contact);
                } catch (Exception e) {
                    System.out.println("Wrong input!");
                }
            }
        } while (!back);
    }

    private void contactMenu(Contact contact) {
        boolean goToMenu = false;
        do {
            System.out.print("\nEnter action (edit, delete, menu):\n");
            String input = scn.nextLine();
            switch (input) {
                case "edit" -> edit(contact);
                case "delete" ->  {
                    remove(contact);
                    goToMenu = true;
                    run();
                }
                case "menu" -> {
                    goToMenu = true;
                    run();
                }
                default -> System.out.println("Wrong input!");
            }
        } while (!goToMenu);
    }

    private void printContacts(List<Contact> contacts) {
        int i = 1;
        for (Contact contact : contacts) {
            System.out.printf("%d. %s\n", i, contact.nameToString());
            i++;
        }
    }

}