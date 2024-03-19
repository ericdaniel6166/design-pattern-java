package com.eric6166.Structural.Composite.Solution;

import java.util.List;

public class Solution {
    public interface Item {
        double cost();
    }

    public static class RealItem implements Item {
        private String name;
        private double price;

        public RealItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public double cost() {
            return price;
        }

    }

    public static class Box implements Item {
        private List<Item> children;

        public Box(List<Item> children) {
            this.children = children;
        }

        @Override
        public double cost() {
            double cost = 0.0;
            if (children != null && !children.isEmpty()) {
                for (var child : children) {
                    cost += child.cost();
                }
            }
            return cost;
        }
    }


    public static void main(String[] args) {
        var box = new Box(List.of(
                new RealItem(
                        "Mouse",
                        20.5
                ),
                new Box(List.of(
                        new RealItem(
                                "Keyboard",
                                60.0
                        ),
                        new RealItem(
                                "Charger",
                                15.0
                        )
                ))
        ));
        System.out.println(String.format("total cost: %s", box.cost()));
    }
}
