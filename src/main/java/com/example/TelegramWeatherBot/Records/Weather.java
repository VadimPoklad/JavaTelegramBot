package com.example.TelegramWeatherBot.Records;

public record Weather(String id, String main, String description, String icon) {
    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}