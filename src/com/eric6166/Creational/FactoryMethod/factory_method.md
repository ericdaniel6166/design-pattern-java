#creational

#factory_method

#(aka: virtual constructor)

#02

Factory Method
is a creational design pattern
that provides an interface
for creating objects in a superclass,
but allows subclasses
to alter the type of objects
that will be created.

Problem
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
    
    class NotifierCreator {
        <<abstract>>
        +Notifier createNotifier()
    }
    
    NotifierCreator ..> Notifier
    SMSNotifierCreator --|> NotifierCreator
    EmailNotifierCreator --|> NotifierCreator
```
