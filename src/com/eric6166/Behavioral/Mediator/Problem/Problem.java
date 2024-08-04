package com.eric6166.Behavioral.Mediator.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem {

    public static class User {
        private String name;
        private List<User> contacts;

        public User(String name) {
            this.name = name;
            this.contacts = new ArrayList<>();
        }

        public void addContact(User user) {
            contacts.add(user);
        }

        public void sendMessage(String message) {
            for (User user : contacts) {
                user.receiveMessage(message, this);
            }
        }

        public void receiveMessage(String message, User sender) {
            System.out.println(name + " received a message from " + sender.getName() + ": " + message);
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        User john = new User("John");
        User jane = new User("Jane");
        User bob = new User("Bob");

        john.addContact(jane);
        john.addContact(bob);
        jane.addContact(john);
        bob.addContact(jane);

        john.sendMessage("Hello everyone!");
        jane.sendMessage("Hi John!");
    }

}
