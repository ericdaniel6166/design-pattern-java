package com.eric6166.Behavioral.Strategy.Solution;

public class Solution {
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
        var sms = new NotificationService(new SMSNotifier());
        sms.sendNotification("Hello world");

        var email = new NotificationService(new EmailNotifier());
        email.sendNotification("Hello world");


    }
}
