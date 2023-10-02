package com.example.TelegramWeatherBot.Records.Setter;

import com.example.TelegramWeatherBot.Records.*;
import com.fasterxml.jackson.databind.JsonNode;


public class Set {
    public static City setCity(JsonNode jsonNode) {
        jsonNode = jsonNode.elements().next();
        String lat = jsonNode.get("lat").asText();
        String lon = jsonNode.get("lon").asText();
        String name = jsonNode.get("name").asText();
        String country = jsonNode.get("country").asText();
        return new City(name,country,lat,lon);
    }

    public static Main setMain(JsonNode jsonNode){
        String temp = Main.toCelsius(jsonNode.get("temp").asText());
        String feels_like= Main.toCelsius(jsonNode.get("feels_like").asText());
        String temp_min= Main.toCelsius(jsonNode.get("temp_min").asText());
        String temp_max= Main.toCelsius(jsonNode.get("temp_max").asText());
        String pressure= jsonNode.get("pressure").asText();
        String sea_level= jsonNode.get("sea_level").asText();
        String grnd_level= jsonNode.get("grnd_level").asText();
        String humidity= jsonNode.get("sea_level").asText();
        String temp_kf= jsonNode.get("temp_kf").asText();

        return new Main(temp, feels_like, temp_min, temp_max, pressure,
                sea_level, grnd_level, humidity,temp_kf);
    }

    public static Weather setWeather(JsonNode jsonNode){
        String id = null;
        String main= null;
        String description= null;
        String icon= null;


        for(JsonNode objNode : jsonNode) {
            id = objNode.get("id").asText();
            main = objNode.get("main").asText();
            description = objNode.get("description").asText();
            icon = objNode.get("icon").asText();
        }

        return new Weather(id, main, description, icon);
    }

    public static Clouds setClouds(JsonNode jsonNode){
        String all = jsonNode.asText();
        return new Clouds(all);
    }

    public static Wind setWind(JsonNode jsonNode){
        String speed = jsonNode.get("speed").asText();
        String deg = jsonNode.get("deg").asText();
        String gust = jsonNode.get("gust").asText();
        return new Wind(speed,deg,gust);
    }

    public static Date setDate(JsonNode jsonNode){
        String dt_txt = jsonNode.asText();
        return new Date(dt_txt);
    }

}
