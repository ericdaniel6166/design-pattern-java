package com.eric6166.Creational.AbstractFactory.Solution;

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
        private VoucherAbstractFactory factory;

        public Voucher(VoucherAbstractFactory factory) {
            this.factory = factory;
        }

        @Override
        public String toString() {
            return "Voucher{" +
                    "drink=" + factory.createDrink() +
                    ", food=" + factory.createFood() +
                    '}';
        }
    }

    public interface VoucherAbstractFactory {
        Drink createDrink();
        Food createFood();

    }

    public static class MorningVoucherFactory implements VoucherAbstractFactory {
        @Override
        public Drink createDrink() {
            return new Coffee();
        }

        //???
//        @Override
//        public Coffee createDrink() {
//            return new Coffee();
//        }



        @Override
        public Food createFood() {
            return new Cake();
        }
    }

    public static class EveningVoucherFactory implements VoucherAbstractFactory {
        @Override
        public Drink createDrink() {
            return new Beer();
        }

        @Override
        public Food createFood() {
            return new GrilledOctopus();
        }
    }

    public static VoucherAbstractFactory createVoucherFactory(String campaignName) {
        if ("creative morning".equalsIgnoreCase(campaignName)) {
            return new MorningVoucherFactory();
        }
        if ("chill all night long".equalsIgnoreCase(campaignName)) {
            return new EveningVoucherFactory();
        }
        throw new IllegalArgumentException(String.format("campaign not found, campaign name: %s", campaignName));
    }

    public static void main(String[] args) {
        System.out.println(List.of(
                new Voucher(createVoucherFactory("creative morning")),
                new Voucher(createVoucherFactory("chill all night long"))
        ));

    }
}
