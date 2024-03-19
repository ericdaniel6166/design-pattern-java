package com.eric6166.Structural.Decorator.Problem;

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

    // If I have more notifiers, I have to define more types to combine them!!!

    public static class EmailSMSNotifier implements Notifier {
        private EmailNotifier emailNotifier;
        private SMSNotifier smsNotifier;

        public EmailSMSNotifier() {
            emailNotifier = new EmailNotifier();
            smsNotifier = new SMSNotifier();
        }

        @Override
        public void sendNotification(String message) {
            emailNotifier.sendNotification(message);
            smsNotifier.sendNotification(message);
        }
    }

    public static Notifier createNotifier(String type) {
        if ("email".equalsIgnoreCase(type)) {
            return new EmailNotifier();
        }
        if ("sms".equalsIgnoreCase(type)) {
            return new SMSNotifier();
        }
        if ("email-sms".equalsIgnoreCase(type)) {
            return new EmailSMSNotifier();
        }

        throw new IllegalArgumentException(String.format("wrong type for Notifier, type: %s", type));
    }
    
    public static void main(String[] args) {
        var service = new NotificationService(createNotifier("email-sms"));
        service.sendNotification("Hello world");

    }
}
