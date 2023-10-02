package com.example.TelegramWeatherBot.Records;

public record Wind(String speed, String deg, String gust) {
    @Override
    public String toString() {
        return "Wind{" +
                "speed='" + speed + '\'' +
                ", deg='" + deg + '\'' +
                ", gust='" + gust + '\'' +
                '}';
    }
}