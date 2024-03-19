package com.eric6166.Structural.Decorator.Solution;

public class Solution {
    public static class NotificationService {
        private NotifierDecorator notifierDecorator;

        public NotificationService(NotifierDecorator notifierDecorator) {
            this.notifierDecorator = notifierDecorator;
        }

        public void sendNotification(String message) {
            notifierDecorator.sendNotification(message);
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

    public static class TelegramNotifier implements Notifier {
        @Override
        public void sendNotification(String message) {
            System.out.println(String.format("Send notification: %s, Sender: Telegram", message));
        }
    }

    public static class NotifierDecorator implements Notifier {
        private NotifierDecorator core;
        private Notifier notifier;

        public NotifierDecorator(Notifier notifier) {
            this.notifier = notifier;
        }

        public NotifierDecorator(NotifierDecorator core, Notifier notifier) {
            this.core = core;
            this.notifier = notifier;
        }

        @Override
        public void sendNotification(String message) {
            if (core != null) {
                core.sendNotification(message);
            }
            if (notifier != null) {
                notifier.sendNotification(message);
            }
        }

        public NotifierDecorator decorate(Notifier notifier) {
            return new NotifierDecorator(this, notifier);
        }

    }

    public static NotifierDecorator createNotifierDecorator(String first, String... types) {
        NotifierDecorator decorator = new NotifierDecorator(createNotifier(first));
        if (types != null && types.length > 0) {
            for (var nextInType: types) {
                decorator = decorator.decorate(createNotifier(nextInType));
            }
        }
        return decorator;

    }

    private static Notifier createNotifier(String type) {
        return switch (type) {
            case "email" -> new EmailNotifier();
            case "sms" -> new SMSNotifier();
            case "telegram" -> new TelegramNotifier();
            default -> throw new IllegalArgumentException(String.format("wrong type for Notifier, type: %s", type));
        };
    }

    public static void main(String[] args) {
        var service = new NotificationService(createNotifierDecorator("email", "sms", "telegram"));
        service.sendNotification("Hello world");

        var otherService = new NotificationService(new NotifierDecorator(new EmailNotifier())
                .decorate(new SMSNotifier())
                .decorate(new TelegramNotifier())
        );
        otherService.sendNotification("Test");

    }
}
