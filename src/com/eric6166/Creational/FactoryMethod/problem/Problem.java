package com.eric6166.Creational.FactoryMethod.problem;


public class Problem {
    public static class NotificationService {
        Notifier notifier;

        public NotificationService(Notifier notifier) {
            this.notifier = notifier;
        }

        public void sendNotification(String message) {
            notifier.sendNotification(message);
        }

    }

    public interface Notifier {
        void sendNotification(String message);
    }

    public static class SMSNotifier implements Notifier {
        @Override
        public void sendNotification(String message) {
            System.out.println(String.format("Send notification: %s, Sender: SMS", message));
        }
    }

    public static class EmailNotifier implements Notifier {
        @Override
        public void sendNotification(String message) {
            System.out.println(String.format("Send notification: %s, Sender: Email", message));
        }
    }

    public static void main(String[] args) {

        // problem: I don't want my users to init a new notifier like this.
        // They should call to something to produce a notifier with its specific type
        // createNotifier(type) Notifier
        // ex: I only want this system to handle only email and sms

        var sms = new NotificationService(new SMSNotifier());
        sms.sendNotification("Hello world");

        var email = new NotificationService(new EmailNotifier());
        email.sendNotification("Hello world");


    }
}
