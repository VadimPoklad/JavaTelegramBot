package com.example.TelegramWeatherBot.JsonPars;


import com.example.TelegramWeatherBot.Records.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import static com.example.TelegramWeatherBot.Records.Setter.Set.*;

public class JsonToWeatherCondition {
    public static List<WeatherCondition> parse(String json, City city){
        List<WeatherCondition> weatherConditionList=new ArrayList<>();
        JsonNode jsonNode;
        try {
            jsonNode = JsonObjectMapper.parse(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        jsonNode.get("list")
                .forEach(o-> weatherConditionList.add(new WeatherCondition(city,
                        setMain(o.get("main")),
                        setWeather(o.get("weather")),
                        setClouds(o.get("clouds")),
                        setWind(o.get("wind")),
                        setDate(o.get("dt_txt")))));
        return weatherConditionList;
    }


}
