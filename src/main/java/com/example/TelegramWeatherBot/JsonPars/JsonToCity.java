package com.example.TelegramWeatherBot.JsonPars;

import com.example.TelegramWeatherBot.Records.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import static com.example.TelegramWeatherBot.Records.Setter.Set.setCity;

public class JsonToCity {
    public static City parse(String json){

        JsonNode jsonNode;
        try {
            jsonNode = JsonObjectMapper.parse(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return setCity(jsonNode);
    }
}
