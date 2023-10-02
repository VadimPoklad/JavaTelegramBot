package com.example.TelegramWeatherBot.Massage;

import java.util.HashMap;
import java.util.Map;

public class Icon {
    private static final Map<String, String> icon = new HashMap<>(){{
        put("Clouds","\uD83C\uDF25");
        put("Thunderstorm","⛈");
        put("Drizzle","\uD83C\uDF26");
        put("Rain","\uD83C\uDF27");
        put("Snow","\uD83C\uDF28");
        put("Mist","\uD83C\uDF2B");
        put("Smoke","\uD83C\uDF2B");
        put("Haze","\uD83C\uDF2B");
        put("Dust","\uD83C\uDF2B");
        put("Fog","\uD83C\uDF2B");
        put("Sand","\uD83C\uDF2B");
        put("Ash","\uD83D\uDCA8");
        put("Squall","\uD83C\uDF2B");
        put("Tornado","\uD83C\uDF2A");
        put("Clear","☀️");}};

    public static String getIcon(String str){
        return icon.get(str);
    }
}
