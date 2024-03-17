package com.eric6166.Creational.AbstractFactory.solution;

import java.util.List;

public class Solution {
    public interface Drink {
        void drink();
    }

    public interface Food {
        void eat();
    }

    public static class Beer implements Drink {
        @Override
        public void drink() {
            System.out.println("drink beer");
        }

        @Override
        public String toString() {
            return "Beer";
        }
    }

    public static class Coffee implements Drink {
        @Override
        public void drink() {
            System.out.println("drink coffee");
        }

        @Override
        public String toString() {
            return "Coffee";
        }
    }

    public static class Cake implements Food {
        @Override
        public void eat() {
            System.out.println("eat cake");
        }

        @Override
        public String toString() {
            return "Cake";
        }
    }

    public static class GrilledOctopus implements Food {
        @Override
        public void eat() {
            System.out.println("eat grilled octopus");
        }

        @Override
        public String toString() {
            return "Grilled Octopus";
        }
    }

    public static class Voucher {
        Drink drink;
        Food food;

        public Voucher(Drink drink, Food food) {
            this.drink = drink;
            this.food = food;
        }

        @Override
        public String toString() {
            return "Voucher{" +
                    "drink=" + drink +
                    ", food=" + food +
                    '}';
        }
    }

    public interface VoucherAbstractFactory {
        Drink getDrink();
        Food getFood();

    }

    public static class MorningVoucherFactory implements VoucherAbstractFactory {
        @Override
        public Drink getDrink() {
            return new Coffee();
        }

        @Override
        public Food getFood() {
            return new Cake();
        }
    }

    public static class EveningVoucherFactory implements VoucherAbstractFactory {
        @Override
        public Drink getDrink() {
            return new Beer();
        }

        @Override
        public Food getFood() {
            return new GrilledOctopus();
        }
    }

    public static VoucherAbstractFactory getVoucherFactory(String campaignName) {
        if ("creative morning".equalsIgnoreCase(campaignName)) {
            return new MorningVoucherFactory();
        }
        if ("chill all night long".equalsIgnoreCase(campaignName)) {
            return new EveningVoucherFactory();
        }
        throw new IllegalArgumentException(String.format("campaign not found, campaign name: %s", campaignName));
    }

    public static Voucher getVoucher(VoucherAbstractFactory voucherAbstractFactory) {
        return new Voucher(voucherAbstractFactory.getDrink(), voucherAbstractFactory.getFood());

    }

    public static void main(String[] args) {
        System.out.println(List.of(
                getVoucher(getVoucherFactory("creative morning")),
                getVoucher(getVoucherFactory("chill all night long"))
        ));

    }
}
