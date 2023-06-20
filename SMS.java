package Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

abstract class SMS {
    private static List<Chat> sentChats = new ArrayList<Chat>();

    static public void sendMessage(PhoneBook.Contact receiver, String content) {
        sentChats.add(new Chat(receiver, content));
    }

    static public void deleteChat(PhoneBook.Contact receiver) {
        Iterator<Chat> iterator = sentChats.iterator();
        while (iterator.hasNext()) {
            Chat chat = iterator.next();
            if (chat.getReceiver().equals(receiver)) {
                iterator.remove();
            }
        }
    }

    static public void printChat(PhoneBook.Contact receiver) {
        for (Chat chat : sentChats) {
            if (chat.getReceiver().getName().equals(receiver.getName())) {
                System.out.println(chat);
            }
        }
    }

    static public void findLine(String line) {
        boolean found = false;
        for (Chat chat : sentChats) {
            if (chat.getContent().contains(line)) {
                System.out.println(chat.getReceiver());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No results found");
        }
    }

    static public void printAllChats() {
        for (Chat chat : sentChats) {
            System.out.println(chat);
        }
    }


    static class Chat {
        private PhoneBook.Contact receiver;
        private String content;
    
        public Chat(PhoneBook.Contact receiver, String content) {
            this.receiver = receiver;
            this.content = content;
        }
    
        public PhoneBook.Contact getReceiver() {
            return receiver;
        }
    
        public String getContent() {
            return content;
        }

        public String toString() {
            return "Receiver: " + receiver +
                   " | Content: " + content;
        }
    }
}
