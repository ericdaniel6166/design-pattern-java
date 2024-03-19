package com.eric6166.Creational.FactoryMethod.Problem;


public class Problem {
    public static class NotificationService {
        private Notifier notifier;

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

    public static Notifier createNotifier(String type) {
        // problem: I don't want my users to init a new notifier like this.
        // They should call to something to produce a notifier with its specific type
        // createNotifier(type) Notifier

        if ("email".equalsIgnoreCase(type)) {
            return new EmailNotifier();
        }
        if ("sms".equalsIgnoreCase(type)) {
            return new SMSNotifier();
        }
        throw new IllegalArgumentException(String.format("wrong type for Notifier, type: %s", type));
    }

    public static void main(String[] args) {

        var sms = new NotificationService(createNotifier("sms"));
        sms.sendNotification("Hello world");

        var email = new NotificationService(createNotifier("email"));
        email.sendNotification("Hello world");

        var wrongType = new NotificationService(createNotifier("wrongType"));


    }
}
