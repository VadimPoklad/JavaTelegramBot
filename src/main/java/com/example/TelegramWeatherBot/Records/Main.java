package com.example.TelegramWeatherBot.Records;

public record Main(String temp, String feels_like, String temp_min, String temp_max, String pressure,
                   String sea_level, String grnd_level, String humidity, String temp_kf) {

   public static String toCelsius(String kelvin){
       int celsius =  (int) Math.round((Double.parseDouble(kelvin) - 273.15));
       return celsius +" C°";
   }

    @Override
    public String toString() {
        return "Main{" +
                "temp='" + temp + '\'' +
                ", feels_like='" + feels_like + '\'' +
                ", temp_min='" + temp_min + '\'' +
                ", temp_max='" + temp_max + '\'' +
                ", pressure='" + pressure + '\'' +
                ", sea_level='" + sea_level + '\'' +
                ", grnd_level='" + grnd_level + '\'' +
                ", humidity='" + humidity + '\'' +
                ", temp_kf='" + temp_kf + '\'' +
                '}';
    }
}
