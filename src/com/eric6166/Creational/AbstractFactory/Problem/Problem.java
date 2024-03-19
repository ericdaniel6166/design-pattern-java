package com.eric6166.Creational.AbstractFactory.Problem;

import java.util.List;

public class Problem {
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
        private Drink drink;
        private Food food;

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


    public static void main(String[] args) {
        System.out.println(List.of(
                new Voucher(
                        new Coffee(),
                        new Cake()
                ),

                new Voucher(
                        new Beer(),
                        new GrilledOctopus()
                ),

                // problem: this Voucher {Coffee, Grilled Octopus} is weird
                new Voucher(
                       new Coffee(),
                       new GrilledOctopus()
                )

        ));

    }
}
