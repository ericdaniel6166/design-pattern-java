package com.eric6166.Creational.FactoryMethod.solution;


public class Solution {
    public static class NotificationService {
        private Notifier notifier;

        public NotificationService(Notifier notifier) {
            this.notifier = notifier;
        }

        //???
//        public NotificationService(NotifierCreator notifierCreator) {
//            this.notifier = notifierCreator.createNotifier();
//        }

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

    public static abstract class NotifierCreator {
        abstract Notifier createNotifier();
    }

    //???
//    public interface NotifierCreator {
//        Notifier createNotifier();
//    }

    public static class SMSNotifierCreator extends NotifierCreator {
        @Override
        Notifier createNotifier() {
            return new SMSNotifier();
        }
    }

    public static class EmailNotifierCreator extends NotifierCreator {
        @Override
        Notifier createNotifier() {
            return new EmailNotifier();
        }
    }

    public static Notifier createNotifier(String type) {
        if ("email".equalsIgnoreCase(type)) {
            return new EmailNotifierCreator().createNotifier();
        }
        if ("sms".equalsIgnoreCase(type)) {
            return new SMSNotifierCreator().createNotifier();
        }
        throw new IllegalArgumentException(String.format("wrong type for Notifier, type: %s", type));
    }


    //???
//    public static NotifierCreator createNotifierNotifierCreator(String type) {
//        if ("email".equalsIgnoreCase(type)) {
//            return new EmailNotifierCreator();
//        }
//        if ("sms".equalsIgnoreCase(type)) {
//            return new SMSNotifierCreator();
//        }
//        throw new IllegalArgumentException(String.format("wrong type for Notifier, type: %s", type));
//    }



    public static void main(String[] args) {
        var sms = new NotificationService(createNotifier("sms"));

        //???
//        var sms = new NotificationService(createNotifierCreator("sms"));

        sms.sendNotification("Hello world");
        var email = new NotificationService(createNotifier("email"));
        email.sendNotification("Hello world");
        var wrongType = new NotificationService(createNotifier("wrongType"));

    }

}


