package com.eric6166.Behavioral.Observer.Solution;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public interface Observer {
        void update(float temperature);
    }

    public interface Subject {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }


    public static class WeatherStation implements Subject {
        private List<Observer> observers;
        private float temperature;

        public WeatherStation() {
            observers = new ArrayList<>();
        }

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(temperature);
            }
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
            notifyObservers();
        }
    }

    public static class DisplayElement implements Observer {
        private String name;

        public DisplayElement(String name) {
            this.name = name;
        }

        @Override
        public void update(float temperature) {
            System.out.println(name + " updated: Temperature is now " + temperature + "°C");
        }
    }

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        DisplayElement phoneDisplay = new DisplayElement("Phone Display");
        DisplayElement webDisplay = new DisplayElement("Web Display");

        weatherStation.registerObserver(phoneDisplay);
        weatherStation.registerObserver(webDisplay);

        weatherStation.setTemperature(25);
        weatherStation.setTemperature(30);
    }

}
