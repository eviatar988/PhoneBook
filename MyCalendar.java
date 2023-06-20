package Phone;
import java.util.Date;

abstract class MyCalendar {
    public static void addEvent(Date date, int length, PhoneBook.Contact contact) {
    }
    public static void addEvent(Date date, int length, String description) {
    }

    public static void deleteEvent(String nameToDelete) {}

    public static void printEventsByDate(Date date) {}

    public static void printEventsByContact(String contactToPrint) {}

    public static void checkForCollisions() {}

    public static void printAllEvents() {}
}
