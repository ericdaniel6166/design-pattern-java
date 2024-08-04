package com.eric6166.Behavioral.Mediator.Solution;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public interface ChatMediator {
        void showMessage(User user, String message);
    }

    public static class ChatRoom implements ChatMediator {
        private List<User> users;

        public ChatRoom() {
            this.users = new ArrayList<>();
        }

        public void addUser(User user) {
            users.add(user);
        }

        @Override
        public void showMessage(User user, String message) {
            for (User u : users) {
                if (u != user) {
                    u.receiveMessage(message, user);
                }
            }
        }
    }

    public static class User {
        private String name;
        private ChatMediator mediator;

        public User(String name, ChatMediator mediator) {
            this.name = name;
            this.mediator = mediator;
        }

        public void sendMessage(String message) {
            System.out.println(name + " sends: " + message);
            mediator.showMessage(this, message);
        }

        public void receiveMessage(String message, User sender) {
            System.out.println(name + " received a message from " + sender.getName() + ": " + message);
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User john = new User("John", chatRoom);
        User jane = new User("Jane", chatRoom);
        User bob = new User("Bob", chatRoom);

        chatRoom.addUser(john);
        chatRoom.addUser(jane);
        chatRoom.addUser(bob);

        john.sendMessage("Hello everyone!");
        jane.sendMessage("Hi John!");
    }
}
