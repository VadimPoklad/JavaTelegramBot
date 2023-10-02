package com.example.TelegramWeatherBot.Records;

import java.util.ArrayList;

public record WeatherCondition(City city, Main main, Weather weather, Clouds clouds,
                               Wind wind, Date date) {
}
