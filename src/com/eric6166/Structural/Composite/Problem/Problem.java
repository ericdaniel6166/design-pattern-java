package com.eric6166.Structural.Composite.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    public static class Item {
        private String name;
        private double price;
        //problem: an item always has children
        private List<Item> children;

        public Item(String name, double price, List<Item> children) {
            this.name = name;
            this.price = price;
            this.children = children;
        }

        public double cost() {
            double cost = price;
            if (children != null && !children.isEmpty()) {
                for (var child : children) {
                    cost += child.cost();
                }
            }
            return cost;
        }
    }


    public static void main(String[] args) {
        var item = new Item(
                "root box",
                0.0,
                new ArrayList<>(List.of(
                        new Item(
                                "Mouse",
                                20.5,
                                null
                        ),
                        new Item(
                                "sub box",
                                0.0,
                                new ArrayList<>(List.of(
                                        new Item(
                                                "Keyboard",
                                                60.0,
                                                null
                                        ),
                                        new Item(
                                                "Charger",
                                                15.0,
                                                null
                                        )
                                ))
                        )
                ))
        );
        System.out.println(String.format("total cost: %s", item.cost()));

    }
}
