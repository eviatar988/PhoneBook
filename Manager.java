package Phone;
import java.util.Date;
import java.util.Scanner;

public abstract class Manager {
    static Scanner sc = new Scanner(System.in);

    public static void runMenu() {
        int choice = 0;
        while (true){
            System.out.println("""
                    Welcome to the application center!
                    which application would you like to run?
                    1. Contacts
                    2. SMS
                    3. Calendar
                    4. Media
                    5. Print all options
                    6. Exit""");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> runContacts();
                case 2 -> runSMS();
                case 3 -> runCalendar();
                case 4 -> runMedia();
                case 5 -> printMenu(0);
                case 6 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid input, please try again");
            }
        }
    }

    public static void printMenu(int choice){
        switch (choice){
            case 1 -> System.out.println("""
                    1. Add a contact
                    2. Delete a contact
                    3. Print all contacts
                    4. Search for a contact
                    5. Sort by name
                    6. Sort by phone number
                    7. Reverse order
                    8. Save to file
                    9. Load from file
                    10. Exit""");
            case 2 -> System.out.println("""
                    1. Send a message
                    2. Delete chat
                    3. Print chat
                    4. Search for a message
                    5. Print all messages
                    6. Exit""");
            case 3 -> System.out.println("""
                    1. Add an event
                    2. Delete an event
                    3. Print all events in a specific date
                    4. Print all events with a contact
                    5. Delete colliding events
                    6. Print all events
                    7. Exit""");
            case 4 -> System.out.println("""
                    1. Add a song/video
                    2. Play a song/video
                    3. Play all media
                    4. Exit""");
            case 0 -> System.out.println("""
                    Contacts:
                        1. Add a contact
                        2. Delete a contact
                        3. Print all contacts
                        4. Search for a contact
                        5. Sort by name
                        6. Sort by phone number
                        7. Reverse order
                        8. Save to file
                        9. Load from file
                        10. Exit
                    SMS:
                        1. Send a message
                        2. Delete chat
                        3. Print chat
                        4. Search for a message
                        5. Print all messages
                        6. Exit
                    Calendar:
                        1. Add an event
                        2. Delete an event
                        3. Print all events in a specific date
                        4. Print all events with a contact
                        5. Delete colliding events
                        6. Print all events
                        7. Exit
                    Media:
                        1. Add a song/video
                        2. Play a song/video
                        3. Play all media
                        4. Exit""");
        }
    }
    public static void runContacts() {
        while (true) {
            printMenu(1);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter new name: ");
                    String name = sc.next();
                    System.out.println("Enter new phone number: ");
                    String phoneNumber = sc.next();
                    PhoneBook.addContact(name, phoneNumber);
                    System.out.println("Contact added successfully!");
                }
                case 2 -> {
                    System.out.println("Enter name to delete: ");
                    String nameToDelete = sc.next();
                    if (!PhoneBook.exists(nameToDelete)) {
                        System.out.println("Contact doesn't exist!");
                        break;
                    }
                    PhoneBook.deleteContact(nameToDelete);
                    SMS.deleteChat(PhoneBook.getContact(nameToDelete));
                    Calendar.deleteEventsByContact(PhoneBook.getContact(nameToDelete));
                    System.out.println("Contact deleted successfully!");
                }
                case 3 -> PhoneBook.printAllContacts();
                case 4 -> {
                    System.out.println("Enter name to search: ");
                    String nameToSearch = sc.next();
                    PhoneBook.searchForContact(nameToSearch);
                }
                case 5 -> {
                    PhoneBook.sortByName();
                    System.out.println("Sorted successfully!");
                }
                case 6 -> {
                    PhoneBook.sortByNumber();
                    System.out.println("Sorted successfully!");
                }
                case 7 -> {
                    PhoneBook.reverseOrder();
                    System.out.println("Reversed successfully!");
                }
                case 8 -> {
                    System.out.println("Enter file name to load to: ");
                    String writeFileName = sc.next();
                    PhoneBook.readWrite(writeFileName, 'w');
                    System.out.println("Saved successfully!");
                }
                case 9 -> {
                    System.out.println("Enter file name to load from: ");
                    String readFileName = sc.next();
                    PhoneBook.readWrite(readFileName, 'r');
                    System.out.println("Loaded successfully!");
                }
                case 10 -> {
                    System.out.println("Exiting contacts...");
                    return;
                }
                default -> System.out.println("You've entered invalid input!\ntry again:");
            }
        }
    }

    public static void runSMS() {
        while (true) {
            printMenu(2);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter name to send to: ");
                    String name = sc.next();
                    if(!PhoneBook.exists(name)){
                        System.out.println("Contact doesn't exist!");
                        break;
                    }
                    System.out.println("Enter message to send: ");
                    String message = sc.next();
                    SMS.sendMessage(PhoneBook.getContact(name), message);
                }
                case 2 -> {
                    System.out.println("Enter name to delete chat: ");
                    String name = sc.next();
                    if(!PhoneBook.exists(name)){
                        System.out.println("Contact doesn't exist!");
                        break;
                    }
                    String nameToDelete = sc.next();
                    SMS.deleteChat(PhoneBook.getContact(nameToDelete));
                }
                case 3 -> {
                    System.out.println("Enter name to print chat: ");
                    String nameToPrint = sc.next();
                    if(!PhoneBook.exists(nameToPrint)){
                        System.out.println("Contact doesn't exist!");
                        break;
                    }
                    SMS.printChat(PhoneBook.getContact(nameToPrint));
                }
                case 4 -> {
                    System.out.println("Enter sentence to search: ");
                    String sentenceToSearch = sc.next();
                    SMS.findLine(sentenceToSearch);
                }
                case 5 -> SMS.printAllChats();
                case 6 -> {
                    return;
                }
                default -> System.out.println("You've entered invalid input!\ntry again:");
            }
        }
    }

    public static void runCalendar() {
        while (true) {
            printMenu(3);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("are you planning an event with a contact? (y/n)");
                    String answer = sc.next();
                    System.out.println("Enter date of event: ");
                    Date date = getDate();
                    System.out.println("Enter event length: ");
                    int length = sc.nextInt();
                    if (answer.equals("y")) {
                        System.out.println("Enter name of contact: ");
                        String contact = sc.next();
                        if (!PhoneBook.exists(contact)) {
                            System.out.println("Contact doesn't exist!");
                            break;
                        }
                        Calendar.addEvent(date, length, PhoneBook.getContact(contact));
                    } else {
                        System.out.println("Enter event description: ");
                        String description = sc.next();
                        Calendar.addEvent(date, length, description);
                    }
                }
                case 2 -> {
                    System.out.println("Enter date of events to delete: ");
                    Date dateToDelete = getDate();
                    Calendar.deleteEvent(dateToDelete);
                }
                case 3 -> {
                    System.out.println("Enter date to print events: ");
                    Date dateToPrint = getDate();
                    Calendar.printEventsByDate(dateToPrint);
                }
                case 4 -> {
                    System.out.println("Enter name of contact to print events: ");
                    String contactToPrint = sc.next();
                    if (!PhoneBook.exists(contactToPrint)) {
                        System.out.println("Contact doesn't exist!");
                        break;
                    }
                    Calendar.printEventsByContact(PhoneBook.getContact(contactToPrint));
                }
                case 5 -> Calendar.checkForCollisions();
                case 6 -> Calendar.printAllEvents();
                case 7 -> {
                    return;
                }
                default -> System.out.println("You've entered invalid input!\ntry again:");
            }
        }
    }

    public static void runMedia() {
        while (true) {
            printMenu(4);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    String name;
                    int length;
                    System.out.println("is that a song or a video? (s/v)");
                    String type = sc.next();
                    if (type.equals("s")){
                        type = "song";
                        System.out.println("Enter song name: ");
                    }
                    else {
                        type = "video";
                        System.out.println("Enter video name: ");
                    }
                    name = sc.next();
                    System.out.println("Enter length of song: ");
                    length = sc.nextInt();
                    Media.addMedia(type, name, length);
                }
                case 2 -> {
                    System.out.println("Enter name of song/video to play: ");
                    String name = sc.next();
                    Media.playByName(name);
                }
                case 3 -> Media.playAllMedia();
                case 4 -> {
                    return;
                }
                default -> System.out.println("You've entered invalid input!\ntry again:");
            }
        }
    }
    public static Date getDate() {
        System.out.println("Enter date year: ");
        int y = sc.nextInt();
        System.out.println("Enter date month: ");
        int m = sc.nextInt();
        m = m - 1;
        System.out.println("Enter date day: ");
        int d = sc.nextInt();
        return new Date(y, m, d);
    }

}
