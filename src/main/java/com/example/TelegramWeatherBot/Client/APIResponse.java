package com.example.TelegramWeatherBot.Client;


import com.example.TelegramWeatherBot.Records.City;


public class APIResponse {
    public static String getCoordinates(String city){

        String addressCoordinates = "https://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s";
        String urlAddress = String.format(addressCoordinates,city,APIKey.APIKey) ;
        return UrlConnection.APICalls(urlAddress);
    }
    public static String getWeatherForecast(City city) {

        String address= "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s";
        String urlAddress = String.format(address, city.lat(), city.lon(), APIKey.APIKey);

        return UrlConnection.APICalls(urlAddress);
    }

}
