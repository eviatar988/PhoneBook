package Phone;
import java.util.Date;
import java.util.ArrayList;

abstract class Calendar {

    private static ArrayList<Event> EventLST = new ArrayList<Event>();

    static public void addEvent(Date date, int length, PhoneBook.Contact contact) {
        EventLST.add(new Event(date, length, contact));
    }

    static public void addEvent(Date date, int length, String description) {
        EventLST.add(new Event(date, length, description));
    }

    static public void deleteEvent(Date date){
        for (int i = 0; i < EventLST.size(); i++) {
            if(EventLST.get(i).date == date)
                EventLST.remove(i);
        }
    }
    static public void printEventsByDate(Date date){
        for (Event event : EventLST) {
            if (event.date == date)
                System.out.println(event.toString());
        }
    }
    static public void printEventsByContact(PhoneBook.Contact contact){
        for (int i = 0; i < EventLST.size(); i++) {
            if(EventLST.get(i).contact == contact)
                System.out.println(EventLST.get(i).toString());
        }
    }
    static public void checkForCollisions(){
        for (int i = 0; i < EventLST.size(); i++) {
            for (int j = 0; j < EventLST.size(); j++) {
                if(EventLST.get(i).date == EventLST.get(j).date)
                    System.out.println("Collision at:" + EventLST.get(i).toString());
            }
        }
    }
    static public void printAllEvents(){
        for (int i = 0; i < EventLST.size(); i++) {
            System.out.println(EventLST.get(i).toString());
        }
    }
    static public void deleteEventsByContact(PhoneBook.Contact contact){
        for (int i = 0; i < EventLST.size(); i++) {
            if(EventLST.get(i).contact == contact)
                EventLST.remove(i);
        }
    }

    static class Event {

        public Date date;
        public int length;
        public PhoneBook.Contact contact;
        public String description;

        public Event(Date date, int length, PhoneBook.Contact contact) {
            this.length = length;
            this.date = date;
            this.contact = contact;
        }
        
        public Event(Date date, int length, String description) {
            this.description = description;
            this.length = length;
            this.date = date;
        }

        public String toString() {
            if (contact != null)
                return "Event{" +
                        "date = '" + date + '\'' +
                        ", length = " + length +
                        ", contact = " + contact +
                        '}';
            else
                return "Event{" +
                        "date = '" + date + '\'' +
                        ", length = " + length +
                        ", description = '" + description + '\'' +
                        '}';
        }

    }


}
