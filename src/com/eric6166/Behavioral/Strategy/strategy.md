#behavioral

#strategy

#01

Strategy
is a behavioral design pattern
that lets you define a family of algorithms,
put each of them into a separate class,
and make their objects interchangeable.

Problem
```mermaid
classDiagram
    class NotificationService {
        -String type
        +NotificationService(String type)
        +sendNotification(String message)
    }
```

Solution
```mermaid
classDiagram
    class NotificationService {
        -Notifier notifier
        +NotificationService(Notifier notifier)
        +sendNotification(String message)
    }
    
    
    class Notifier {
        <<interface>>
        +sendNotification(String message)
    }
    
    
    NotificationService o--> Notifier
    
    class SMSNotifier
    class EmailNotifier
    SMSNotifier ..|> Notifier
    EmailNotifier ..|> Notifier
    
```

