package com.dailycodebuffer.mq;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ConsumerBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void writeMessage(CustomMessage message){

        SendMessage sm = new SendMessage();
        sm.setText("Викладач " + message.getAuthorName() + " почав віддалену пару і " + "\n" + message.getMessage());
        sm.setChatId(String.valueOf(message.getAuthorID()));
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
