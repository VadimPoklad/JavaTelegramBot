package com.example.TelegramWeatherBot.Records;

public record Date(String dt_txt) {

    public String getDate(){
        return dt_txt.substring(0,dt_txt.indexOf(" "));
    }
    public String getTime(){
        return dt_txt.substring(dt_txt.indexOf(" ")+1);
    }

    @Override
    public String toString() {
        return "Date{" +
                "dt_txt='" + dt_txt + '\'' +
                '}';
    }
}