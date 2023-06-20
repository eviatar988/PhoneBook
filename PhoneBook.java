package Phone;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class PhoneBook {
     static class Contact {
        private String name;
        private String phoneNumber;

        //constructor
        public Contact(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        //getters
        public String getName() {
            return this.name;
        }
        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        //setters
        public void setName(String name) {
            this.name = name;
        }
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        //comperators
        public int compareByName(Contact contact) {
            return this.name.compareTo(contact.getName());
        }
        public int compareByNumber(Contact contact) {
            return this.phoneNumber.compareTo(contact.getPhoneNumber());
        }

        public String toString() {
            return this.name + ": " + this.phoneNumber;
        }
    }
    //building a phone book class with name and phone number
    private static ArrayList<Contact> phonesList = new ArrayList<Contact>();

    static public boolean exists(String name) {
        for (Contact contact : phonesList) {
            if (contact.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //add contact
    static public void addContact(String name, String phoneNumber) {
        if (exists(name)) {
            System.out.println("Contact already exists");
            return;
        }
        Contact newContact = new Contact(name, phoneNumber);
        phonesList.add(newContact);
    }

    //delete contact
    static public void deleteContact(String nameToDelete) {
        for (int i = 0; i < phonesList.size(); i++) {
            if (phonesList.get(i).getName().equals(nameToDelete)) {
                phonesList.remove(i);
            }
        }
    }

    //print all contacts
    static public void printAllContacts() {
        for (Contact contact : phonesList) {
            System.out.println(contact);
        }
    }

    //search for a contact
    static public void searchForContact(String nameToSearch) {
        for (Contact contact : phonesList) {
            if (contact.getName().equals(nameToSearch)) {
                System.out.println(contact.toString());
            }
        }
    }

    //sort by name
    static public void sortByName() {
        phonesList.sort(Contact::compareByName);
    }

    //sort by phone number
    static public void sortByNumber() {
        phonesList.sort(Contact::compareByNumber);
    }

    //reverse order
    static public void reverseOrder() {
        ArrayList<Contact> reversedList = new ArrayList<Contact>();
        for (int i = phonesList.size() - 1; i >= 0; i--) {
            reversedList.add(phonesList.get(i));
        }
        phonesList = reversedList;
    }

    static public void readWrite(String fileName, char mode) {
        try {
            File file = new File(fileName);
            if (mode == 'r') {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String[] line = sc.nextLine().split(" ");
                    phonesList.add(new Contact(line[0], line[1]));
                }
                sc.close();
            } else if (mode == 'w') {
                FileWriter writer = new FileWriter(fileName, false);
                for (Contact contact : phonesList) {
                    writer.write(contact.toString());
                }
                writer.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("There is no such file!");
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    static public Contact getContact(String name){
        for (Contact contact : phonesList) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }
}
