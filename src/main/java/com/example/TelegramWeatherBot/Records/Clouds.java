package com.example.TelegramWeatherBot.Records;

public record Clouds(String all) {
    @Override
    public String toString() {
        return "Clouds{" +
                "all='" + all + '\'' +
                '}';
    }
}