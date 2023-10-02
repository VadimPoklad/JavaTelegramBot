package com.example.TelegramWeatherBot.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnection {
    public static String APICalls(String urlAddress) {

        URLConnection urlConnection;
        URL url;
        InputStreamReader isr;
        BufferedReader bfr;

        {
            try {
                url = new URL(urlAddress);
                urlConnection = url.openConnection();
                isr = new InputStreamReader(urlConnection.getInputStream());
                bfr = new BufferedReader(isr);

                String line= bfr.readLine();

                isr.close();;
                bfr.close();

                return line;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
