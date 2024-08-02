package com.eric6166.Structural.Facade.Problem;

import java.util.List;

public class Problem {
    public static class Product {
        private String name;
        private Double price;

        public Product(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Double getPrice() {
            return price;
        }
    }

    public static class Inventory {
        private List<Product> products;

        public Inventory(List<Product> products) {
            this.products = products;
        }

        public Product lookup(String name) throws Exception {
            for (var product : products) {
                if (product.getName().equals(name)) {
                    return product;
                }
            }
            throw new Exception("product not found");
        }
    }

    public static class Account {
        private String name;
        private Double balance;

        public Account(String name, Double balance) {
            this.name = name;
            this.balance = balance;
        }

        public void deposit(Double money) {
            balance += money;
        }

        public void withdraw(Double money) throws Exception {
            if (balance < money) {
                throw new Exception("not enough balance in account");
            }
            balance -= money;
        }

        public String getName() {
            return name;
        }

        public Double getBalance() {
            return balance;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "name='" + name + '\'' +
                    ", balance=" + balance +
                    '}';
        }
    }

    public static class AccountStorage {
        private List<Account> accounts;

        public AccountStorage(List<Account> accounts) {
            this.accounts = accounts;
        }

        public Account lookup(String name) throws Exception {
            for (var account : accounts) {
                if (account.getName().equals(name)) {
                    return account;
                }
            }
            throw new Exception("account not found");
        }

        @Override
        public String toString() {
            return "AccountStorage{" +
                    "accounts=" + accounts +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        var inventory = new Inventory(List.of(
                new Product("Apple", 2.5),
                new Product("Orange", 3.0)
        ));

        var accountStorage = new AccountStorage(List.of(
                new Account("VIP", 1000.0),
                new Account("Economic", 300.0)

        ));

        var productName = "Apple";
        var accountName = "VIP";

        Product product = inventory.lookup(productName);

        Account account = accountStorage.lookup(accountName);

        account.withdraw(product.getPrice());

        System.out.println(String.format("accountStorage: %s", accountStorage));

        // Problem: Problem if adding more steps to buying process,
        // There are too many steps, not sure if I do correctly
    }


}
