package com.eric6166.Structural.Facade.Solution;


import java.util.List;

public class Solution {
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

        public void withdraw(Double money) {
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

    public static class FacadeService {
        private Inventory inventory;
        private AccountStorage accountStorage;

        public FacadeService(Inventory inventory, AccountStorage accountStorage) {
            this.inventory = inventory;
            this.accountStorage = accountStorage;
        }

        public void buyProduct(String productName, String accountName) throws Exception {
            var product = inventory.lookup(productName);
            var account = accountStorage.lookup(accountName);
            if (account.getBalance().compareTo(product.getPrice()) < 0) {
                throw new Exception("not enough balance in account");
            }
            account.withdraw(product.getPrice());
        }

        public void deposit(Double money, String accountName) throws Exception {
            var account = accountStorage.lookup(accountName);
            account.deposit(money);
        }

        public Double fetchBalance(String accountName) throws Exception {
            var account = accountStorage.lookup(accountName);
            return account.getBalance();
        }


    }

    public static void main(String[] args) throws Exception {
        var facadeService = new FacadeService(
                new Inventory(List.of(
                        new Product("Apple", 2.5),
                        new Product("Orange", 3.0)
                )),
                new AccountStorage(List.of(
                        new Account("VIP", 1000d),
                        new Account("Economic", 300d)

                ))

        );

        var productName = "Apple";
        var accountName = "VIP";

        facadeService.buyProduct(productName, accountName);
        System.out.println(String.format("account name: %s, balance: %s", accountName, facadeService.fetchBalance(accountName)));;
    }
}
