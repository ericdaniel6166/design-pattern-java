#creational

#abstract_factory

#03

Abstract Factory
is a creational design pattern
that lets you produce
families of related objects
without specifying
their concrete classes.


Problem
```mermaid
classDiagram
    class Drink {
        <<interface>>
        +drink()
    }
    class Food {
        <<interface>>
        +eat()
    }
    
    
    Beer ..|> Drink
    Coffee ..|> Drink
    Voucher o--> Drink
    Voucher o--> Food
    Cake ..|> Food
    GrilledOctopus ..|> Food
    
    class Voucher {
        -Drink drink
        -Food food
        +Voucher(Drink drink, Food food)
    }
    
    
    
```

Solution
```mermaid
classDiagram
    class Drink {
        <<interface>>
        +drink()
    }
    class Food {
        <<interface>>
        +eat()
    }
    Beer ..|> Drink
    GrilledOctopus ..|> Food
    Coffee ..|> Drink
    Cake ..|> Food
    
    
    
    class Voucher {
        -VoucherAbstractFactory factory
        +Voucher(VoucherAbstractFactory factory)
        +doSomething()
    }
    
    class VoucherAbstractFactory {
        <<interface>>
        +Drink createDrink()
        +Food createFood()
        
    }
    Voucher o--> VoucherAbstractFactory
    MorningVoucherFactory ..|> VoucherAbstractFactory
    
    class MorningVoucherFactory {
        +Coffee createDrink()
        +Cake createFood()
    }
    
    class EveningVoucherFactory {
        +Beer createDrink()
        +GrilledOctopus createFood()
    }
    
    MorningVoucherFactory ..> Coffee
    EveningVoucherFactory ..> Beer
    
    MorningVoucherFactory ..> Cake
    EveningVoucherFactory ..|> VoucherAbstractFactory
    
    EveningVoucherFactory ..> GrilledOctopus
    
    
    
    
    
    
    
    
```
```text
doSomething()
...
Drink drink = factory.createDrink()
Food food = factory.createFood()
...
```


