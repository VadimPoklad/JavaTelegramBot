package com.example.TelegramWeatherBot.Massage;

import com.example.TelegramWeatherBot.Bot.Response;
import com.example.TelegramWeatherBot.Records.WeatherCondition;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OutWeatherForecast {
    public static List<String> prepareMassage(List<WeatherCondition> weatherConditionList, Response response){
        List<String> massages;
        if(response.isToday()){
            massages = today(weatherConditionList);
        }else{
            massages = fewDays(weatherConditionList);
        }
        return massages;
    }

    public static String shortInfo(WeatherCondition weatherCondition){
        String str = """
                Time: %s   %s %s | %s
                
                Temp: %s| Feels like: %s| Wind speed: %s|  Pressure: %s
                ________________________________________________________________________
                """;

        str= String.format(str,
                weatherCondition.date().getTime(),
                weatherCondition.weather().main(),
                Icon.getIcon(weatherCondition.weather().main()),
                weatherCondition.weather().description(),
                weatherCondition.main().temp(),
                weatherCondition.main().feels_like(),
                weatherCondition.wind().speed(),
                weatherCondition.main().pressure()
                );
        return str;
    }


    public static List<String> today(List<WeatherCondition> weatherConditionList){
        List<String> massage = new ArrayList<>();

        massage.add(title(weatherConditionList.get(0)));

        weatherConditionList.stream()
                .filter(o->o.date().getDate().equals(String.valueOf(LocalDate.now())))
                .toList()
                .forEach(o->massage.add(shortInfo(o)));


        return massage.stream().reduce((str1, str2) -> str1 + str2).stream().toList();
    }

    public static List<String> fewDays(List<WeatherCondition> weatherConditionList){
        List<String> massages = new ArrayList<>();

        List<String> massage = new ArrayList<>();

        massage.add(title(weatherConditionList.get(0)));
        final String[] tempDate = {weatherConditionList.get(0).date().getDate()};

        weatherConditionList.forEach(o->{
            if(tempDate[0].equals(o.date().getDate())) {
                massage.add(shortInfo(o));
            }else {
                massages.addAll(massage.stream().reduce((str1, str2) -> str1 + str2).stream().toList());
                massage.clear();
                tempDate[0] = o.date().getDate();
                massage.add(title(o));
                massage.add(shortInfo(o));
            }
        });

        return massages;
    }

    public static String title(WeatherCondition weatherCondition){
        String title ="""
                %s  %s  Date: %s
                ________________________________________________________________________
                """;
        return String.format(title,
                weatherCondition.city().country(),
                weatherCondition.city().name(),
                weatherCondition.date().getDate());
    }
}
