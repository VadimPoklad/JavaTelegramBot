package com.example.TelegramWeatherBot.Bot;

import com.example.TelegramWeatherBot.Client.APIResponse;
import com.example.TelegramWeatherBot.JsonPars.JsonToCity;
import com.example.TelegramWeatherBot.JsonPars.JsonToWeatherCondition;
import com.example.TelegramWeatherBot.Massage.OutWeatherForecast;
import com.example.TelegramWeatherBot.Records.City;
import com.example.TelegramWeatherBot.Records.WeatherCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@Component

public class WeatherBot extends TelegramLongPollingBot{

    private boolean enterName = false;
    private final Response response = new Response();
    private static final Logger LOG =  LoggerFactory.getLogger(WeatherBot.class);
    public static final String START = "/start";
    public static final String TODAY = "/today";
    public static final String FEWDAYS = "/fewDays";
    public static final String HELP = "/help";

    public WeatherBot(@Value("${bot.token}") String botToken) {
        super (botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }
        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        if (!enterName) {
            switch (message) {
                case START -> {
                    String userName = update.getMessage().getChat().getUserName();
                    startCommand(chatId, userName);
                }

                case FEWDAYS -> fewDaysCommand(chatId);
                case TODAY -> todayCommand(chatId);


                case HELP -> helpCommand(chatId);
                default -> {
                    System.out.println(message);
                    unknownCommand(chatId);}
            }
        }else outcomeCommand(chatId,message);
    }

    @Override
    public String getBotUsername() {
        return "kneu_weather_bot";
    }

    private void startCommand(Long chatId, String userName){
        var text = """
                Welcome to the Weather bot, %s!
                ______________________________________
                Select:
                /today
                
                or
                
                /fewDays
                ______________________________________
                /help
                """;
        var formattedText = String.format(text,userName);
        sendMessage(chatId, formattedText);
    }

    private void helpCommand(Long chatId){
        sendMessage(chatId, """
                Choose for how many days you need a weather forecast
                using the commands /today or /fewDays, after enter the name of the city.
                If the name is not correct, then the bot will warn you.
                If there is no answer, then please try again later.
                Please use:
                /start
                """);
    }

    private void fewDaysCommand(Long chatId){
        enterNameMassage(chatId);
        response.setToday(false);
        enterName = true;
    }

    private void todayCommand(Long chatId){
        enterNameMassage(chatId);
        response.setToday(true);
        enterName = true;
    }

    private void outcomeCommand(Long chatId, String massage){

        String cityJson = APIResponse.getCoordinates(massage);

        if (cityJson.equals("[]")){
            invalidName(chatId);
            return;
        }
        City city = JsonToCity.parse(cityJson);

        String weatherJson = APIResponse.getWeatherForecast(city);
        List<WeatherCondition> weatherConditionArrayList = JsonToWeatherCondition.parse(weatherJson, city);

        List<String> massages = OutWeatherForecast.prepareMassage(weatherConditionArrayList,response);
        massages.forEach(o->sendMessage(chatId,o));

        enterName = false;
    }

    private void enterNameMassage(Long chatId){
        sendMessage(chatId, "Enter city name:");
    }

    private void invalidName(Long chatId){
        sendMessage(chatId, "Invalid name, please check the correct spelling of the city name");
    }
    private void unknownCommand(Long chatId){
        sendMessage(chatId, """
                Unknown command, please use:
                /start
                
                or
                
                /help
                """);
    }

    private void sendMessage(Long chatId, String text){
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);

        try {
            execute(sendMessage);
        }catch (TelegramApiException e){
            LOG.error("Send message Error",e);
        }
    }



}
