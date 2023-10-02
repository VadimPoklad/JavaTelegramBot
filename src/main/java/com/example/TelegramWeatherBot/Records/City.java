package com.example.TelegramWeatherBot.Records;

public record City(String name, String country, String lat, String lon) {
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
