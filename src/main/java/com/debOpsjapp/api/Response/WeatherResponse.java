package com.debOpsjapp.api.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Current current;
    private Location location;

    // --- Getters & Setters ---
    public Current getCurrent() {
        return current;
    }
    public void setCurrent(Current current) {
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    // Only the fields you care about inside 'current'
    public static class Current {
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        private int temperature;

        @JsonProperty("feelslike")
        private int feelsLike;

        private int humidity;

        public List<String> getWeatherDescriptions() {
            return weatherDescriptions;
        }
        public void setWeatherDescriptions(List<String> weatherDescriptions) {
            this.weatherDescriptions = weatherDescriptions;
        }

        public int getTemperature() {
            return temperature;
        }
        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getFeelsLike() {
            return feelsLike;
        }
        public void setFeelsLike(int feelsLike) {
            this.feelsLike = feelsLike;
        }

        public int getHumidity() {
            return humidity;
        }
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    // Only localtime from 'location'
    public static class Location {
        private String localtime;

        public String getLocaltime() {
            return localtime;
        }
        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }
    }
}
