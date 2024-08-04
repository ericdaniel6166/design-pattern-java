package com.eric6166.Behavioral.Observer.Problem;

public class Problem {

    public static class WeatherStation {
        private float temperature;

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
            // Currently, nothing happens when the temperature changes
        }
    }

    public static class DisplayElement {
        public void update(float temperature) {
            System.out.println("Display updated: Temperature is now " + temperature + "°C");
        }
    }



    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        DisplayElement displayElement = new DisplayElement();

        weatherStation.setTemperature(25);
        // We want the display element to update automatically when the temperature changes
    }

}
