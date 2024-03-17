package com.eric6166.Behavioral.Strategy.Problem;

public class Problem {

    public static class NotificationService {
        String type;

        public NotificationService(String type) {
            this.type = type;
        }


        public void sendNotification(String message) {
            if ("email".equalsIgnoreCase(type)) {
                System.out.println(String.format("Send notification: %s, Sender: Email", message));
                return;
            }
            if ("sms".equalsIgnoreCase(type)) {
                System.out.println(String.format("Send notification: %s, Sender: SMS", message));
                return;
            }
            // problem: new type -> modify NotificationService
            System.out.println(String.format("wrong type for notification service, type: %s", type));
            return;
        }
    }

    public static void main(String[] args) {
        var email = new NotificationService("email");
        email.sendNotification("Hello world");
        var sms = new NotificationService("sms");
        sms.sendNotification("Hello world");
        var wrongType = new NotificationService("wrongType");
        wrongType.sendNotification("Hello world");


    }

}
